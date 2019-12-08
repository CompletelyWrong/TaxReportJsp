package command.user;

import command.Command;
import domain.Report;
import entity.report.ReportStatus;
import exception.ReportFileException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import service.ReportService;
import util.JsonHelper;
import util.XmlHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

public class UpdateReportByFileCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UpdateReportByFileCommand.class);

    private final ReportService reportService;
    private final JsonHelper jsonHelper;
    private final XmlHelper xmlHelper;

    public UpdateReportByFileCommand(ReportService reportService, JsonHelper jsonHelper, XmlHelper xmlHelper) {
        this.reportService = reportService;
        this.jsonHelper = jsonHelper;
        this.xmlHelper = xmlHelper;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long reportId = Long.valueOf(request.getParameter("reportId"));
        Report report = reportService.findById(reportId);
        request.setAttribute("reportId", report.getId());

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        File file = new File(report.getFileLink());
                        String contentType = item.getContentType();
                        switch (contentType) {
                            case "text/json":
                            case "application/json": {
                                jsonHelper.updateJsonFileByFileContent(file, item.getString("UTF-8"));
                                updateReport(report);
                                break;
                            }
                            case "application/xml":
                            case "text/xml": {
                                xmlHelper.updateXmlFileByFileContent(file, item.getString("UTF-8"));
                                updateReport(report);
                                break;
                            }
                            default: {
                                LOGGER.warn("Your file has wrong structure");
                                throw new ReportFileException("Your file has wrong structure");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                LOGGER.warn("Your file has wrong structure");
                throw new ReportFileException("Your file has wrong structure");
            }

            return "/user?command=report&reportId=" + report.getId();
        }

        return "/user/update-report-f.jsp";
    }

    private void updateReport(Report report) {
        Report updatedReport = Report.builder(report)
                .withStatus(ReportStatus.UPDATED)
                .withId(report.getId())
                .build();
        reportService.updateReport(updatedReport);
    }
}
