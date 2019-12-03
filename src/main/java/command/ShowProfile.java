package command;

import entity.user.Role;

import javax.servlet.http.HttpServletRequest;

public class ShowProfile implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");
        switch (role) {
            case INSPECTOR:
                return "/inspector/i-profile.jsp";
            case INDIVIDUAL_TAXPAYER:
            case LEGAL_TAXPAYER:
                return "/user/u-profile.jsp";
            default:
                return "/";
        }

    }
}
