package command;

import entity.user.Role;

import javax.servlet.http.HttpServletRequest;

public class ShowProfileCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Role role = getRole(request);
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
