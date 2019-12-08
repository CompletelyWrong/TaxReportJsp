package command.user;

import command.Command;
import domain.Report;
import domain.ReportStructure;
import entity.report.ReportStatus;
import service.ReportService;
import util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Objects;

public class UpdateReportCommand implements Command {
    private final ReportService reportService;
    private final JsonHelper jsonHelper;

    public UpdateReportCommand(ReportService reportService, JsonHelper jsonHelper) {
        this.reportService = reportService;
        this.jsonHelper = jsonHelper;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long reportId = Long.valueOf(request.getParameter("reportId"));
        Report report = reportService.findById(reportId);
        ReportStructure reportStructure = jsonHelper.readFromJson(report.getFileLink());
        request.setAttribute("report", reportStructure);
        request.setAttribute("reportId", report.getId());

        if (Objects.nonNull(request.getParameter("pib"))) {
            ReportStructure reportStructureValues = getReportStructureValues(request);
            File file = new File(report.getFileLink());
            jsonHelper.updateJsonFileByForm(file, reportStructureValues);
            Report updaterReport = Report.builder(report)
                    .withFileLink(file.getAbsolutePath())
                    .withStatus(ReportStatus.UPDATED)
                    .withId(report.getId())
                    .build();
            reportService.updateReport(updaterReport);

            return "/user?command=report&reportId=" + report.getId();
        }

        return "/user/update-report.jsp";
    }
}
