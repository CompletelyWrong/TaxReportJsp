package filter;

import entity.user.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String adminUrl = "/admin";
        final String userUrl = "/user";
        final String inspectorUrl = "/inspector";
        final String currentUrl = request.getRequestURL().toString();
        final Role role = (Role) request.getSession().getAttribute("role");

        if (currentUrl.contains(adminUrl) && (role != Role.ADMIN)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        if (currentUrl.contains(userUrl) && (role != Role.INDIVIDUAL_TAXPAYER) &&  (role != Role.LEGAL_TAXPAYER)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }



        if (currentUrl.contains(inspectorUrl) && (role != Role.INSPECTOR)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        filterChain.doFilter(request, servletResponse);
    }
}
