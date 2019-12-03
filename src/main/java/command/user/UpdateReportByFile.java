package command.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.Command;
import domain.Report;
import domain.ReportStructure;
import entity.report.ReportStatus;
import exception.ReportFileException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

public class UpdateReportByFile implements Command {
    private final ReportService reportService;

    public UpdateReportByFile(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long reportId = Long.valueOf(request.getParameter("report_id"));
        Report report = reportService.findById(reportId);
        request.setAttribute("report_id", report.getId());

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
                                forJson(file, item.getString("UTF-8"));
                                updateReport(request, report);
                                break;
                            }
                            case "application/xml":
                            case "text/xml": {
                                forXMl(file, item.getString("UTF-8"));
                                updateReport(request, report);
                                break;
                            }
                            default: {
                                throw new ReportFileException("Ur file was corrupted");
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                throw new ReportFileException("Ur file was corrupted");
            }
            return "/user?command=report&report_id=" + report.getId();
        }
        return "/user/update-report-f.jsp";
    }

    private void updateReport(HttpServletRequest request, Report report) {
        Report updatedReport = Report.builder(report)
                .withStatus(ReportStatus.UPDATED)
                .build();
        reportService.updateReportById(updatedReport, report.getId());
    }

    private void forJson(File file, String content) {
        Gson gson = new Gson();
        ReportStructure reportStructure = gson.fromJson(content, ReportStructure.class);
        try (Writer writer = new FileWriter(file)) {
            gson = new GsonBuilder().create();
            gson.toJson(reportStructure, writer);
        } catch (IOException e) {
            throw new ReportFileException("Ur file was corrupted");
        }
    }

    private void forXMl(File file, String content) {
        try (Writer writer = new FileWriter(file)) {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReportStructure.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ReportStructure reportStructure = (ReportStructure) jaxbUnmarshaller.unmarshal(new StringReader(content));
            Gson gson = new GsonBuilder().create();
            gson.toJson(reportStructure, writer);
        } catch (JAXBException | IOException e) {
            throw new ReportFileException("Ur file was corrupted");
        }
    }
}
