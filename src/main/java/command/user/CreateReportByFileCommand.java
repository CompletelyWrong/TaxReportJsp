package command.user;

import command.Command;
import domain.Report;
import domain.User;
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
import java.time.LocalDateTime;
import java.util.List;

public class CreateReportByFileCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(CreateReportByFileCommand.class);

    private final ReportService reportService;
    private final JsonHelper jsonHelper;
    private final XmlHelper xmlHelper;

    public CreateReportByFileCommand(ReportService reportService, JsonHelper jsonHelper, XmlHelper xmlHelper) {
        this.reportService = reportService;
        this.jsonHelper = jsonHelper;
        this.xmlHelper = xmlHelper;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        String contentType = item.getContentType();
                        switch (contentType) {
                            case "text/json":
                            case "application/json": {
                                String filePath = jsonHelper.createJsonFileByFileContent(item.getString("UTF-8"));
                                createReport(request, filePath);
                                break;
                            }
                            case "application/xml":
                            case "text/xml": {
                                String filePath = xmlHelper.createXmlFileByFileContent(item.getString("UTF-8"));
                                createReport(request, filePath);
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
                throw new ReportFileException("Your file has wrong structure");
            }
        }

        return "/user/create-report-f.jsp";
    }

    private void createReport(HttpServletRequest request, String filePath) {
        User user = getUser(request);
        Report report = Report.builder()
                .withCreationDate(LocalDateTime.now())
                .withUser(user)
                .withFileLink(filePath)
                .withStatus(ReportStatus.NEW)
                .build();
        reportService.addReportToUser(report, user);
    }
}
