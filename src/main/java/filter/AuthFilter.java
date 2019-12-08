package filter;

import entity.user.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();
        String page;

        if (nonNull(session) &&
                nonNull(session.getAttribute("role"))) {

            Role role = (Role) session.getAttribute("role");

            switch (role) {
                case ADMIN:
                    page = req.getContextPath() + "/admin?command=users";
                    break;
                case INSPECTOR:
                    page = req.getContextPath() + "/inspector?command=users";
                    break;
                case INDIVIDUAL_TAXPAYER:
                case LEGAL_TAXPAYER:
                    page = req.getContextPath() + "/user?command=show";
                    break;
                default:
                    page = req.getContextPath() + "/";
            }
            res.sendRedirect(page);
            filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
    }
}
