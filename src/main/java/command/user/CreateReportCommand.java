package command.user;

import command.Command;
import domain.Report;
import domain.ReportStructure;
import domain.User;
import entity.report.ReportStatus;
import service.ReportService;
import util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreateReportCommand implements Command {
    private final ReportService reportService;
    private final JsonHelper jsonHelper;

    public CreateReportCommand(ReportService reportService, JsonHelper jsonHelper) {
        this.reportService = reportService;
        this.jsonHelper = jsonHelper;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("pib"))) {
            ReportStructure formValues = getReportStructureValues(request);
            User user = getUser(request);
            String filePath = jsonHelper.createJsonFileByForm(formValues);

            Report report = Report.builder()
                    .withCreationDate(LocalDateTime.now())
                    .withUser(user)
                    .withFileLink(filePath)
                    .withStatus(ReportStatus.NEW)
                    .build();
            reportService.addReportToUser(report, user);
        }

        return "/user/create-report.jsp";
    }
}
