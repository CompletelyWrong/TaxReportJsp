package command;

import domain.Inspector;
import domain.ReportStructure;
import domain.User;
import entity.user.Role;
import exception.NotEqualsPasswordException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public interface Command {
    String execute(HttpServletRequest request);

    default void paginating(HttpServletRequest request, int rows, int currentPage, int recordsPerPage) {
        int numberOfPages = rows / recordsPerPage;

        if (numberOfPages % recordsPerPage > 0  && rows % recordsPerPage != 0) {
            numberOfPages += 1;
        }

        request.setAttribute("numberOfPages", numberOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
    }

    default int parseIntParameter(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException | NullPointerException e) {
            return 1;
        }
    }

    default int parseRecordsPerPage(HttpServletRequest request, String param) {
        try {
            return Integer.parseInt(request.getParameter(param));
        } catch (NumberFormatException | NullPointerException e) {
            return 10;
        }
    }

    default Map<String, String> getGeneralProfileValue(HttpServletRequest request) {
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("repeatedPassword");

        if (!Objects.equals(password, confirmPassword)) {
            throw new NotEqualsPasswordException("Your password is not equals");
        }

        Map<String, String> parameters = new HashMap<>();
        parameters.put("surname", request.getParameter("surname"));
        parameters.put("name", request.getParameter("name"));
        parameters.put("patronymic", request.getParameter("patronymic"));
        parameters.put("email", request.getParameter("email"));
        parameters.put("password", password);

        return parameters;
    }

    default ReportStructure getReportStructureValues(HttpServletRequest request) {
        final String pib = request.getParameter("pib");
        final String type = request.getParameter("type");
        final String code = request.getParameter("code");
        final String dateStart = request.getParameter("dateStart");
        final String dateEnd = request.getParameter("dateEnd");
        final String incomeCode = request.getParameter("incomeCode");
        final String incomeValue = request.getParameter("incomeValue");
        final String outcomeCode = request.getParameter("outcomeCode");
        final String outcomeValue = request.getParameter("outcomeValue");
        final String percentCode = request.getParameter("percentCode");
        final String percentValue = request.getParameter("percentValue");
        final String clearCode = request.getParameter("clearCode");
        final String clearValue = request.getParameter("clearValue");

        return ReportStructure.builder()
                .setFullName(pib)
                .setInnCode(type)
                .setType(code)
                .setClearCode(clearCode)
                .setClearValue(clearValue)
                .setIncomeCode(incomeCode)
                .setIncomeValue(incomeValue)
                .setOutcomeCode(outcomeCode)
                .setOutcomeValue(outcomeValue)
                .setPercentCode(percentCode)
                .setPercentValue(percentValue)
                .setPeriodEnd(dateEnd)
                .setPeriodStart(dateStart)
                .build();
    }

    default Role getRole(HttpServletRequest request) {
        return (Role) request.getSession().getAttribute("role");
    }

    default User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    default Inspector getInspector(HttpServletRequest request) {
        return (Inspector) request.getSession().getAttribute("user");
    }
}
