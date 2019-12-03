package command.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.Command;
import domain.Report;
import domain.ReportStructure;
import domain.User;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class CreateReportByFile implements Command {
    private final ReportService reportService;

    public CreateReportByFile(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multipart = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : multipart) {
                    if (!item.isFormField()) {
                        File file = new File("C:\\epam_servlet_app\\web\\files\\" + File.separator + LocalDate.now() + "-" + LocalTime.now().getNano() + ".json");
                        String contentType = item.getContentType();
                        switch (contentType) {
                            case "text/json":
                            case "application/json": {
                                forJson(file, item.getString("UTF-8"));
                                createReport(request, file);
                                break;
                            }
                            case "application/xml":
                            case "text/xml": {
                                forXMl(file, item.getString("UTF-8"));
                                createReport(request, file);
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
        }
        return "/user/create-report-f.jsp";
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

    private void createReport(HttpServletRequest request, File file) {
        User user = (User) request.getSession().getAttribute("user");
        Report report = Report.builder()
                .withCreationDate(LocalDateTime.now())
                .withUser(user)
                .withFileLink(file.getAbsolutePath())
                .withStatus(ReportStatus.NEW)
                .build();
        reportService.addReportToUser(report, user);
    }
}
