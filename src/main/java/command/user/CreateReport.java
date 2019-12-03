package command.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.Command;
import domain.Report;
import domain.ReportStructure;
import domain.User;
import entity.report.ReportStatus;
import exception.ReportFileException;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreateReport implements Command {
    private final ReportService reportService;

    public CreateReport(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("pib"))) {
            final String pib = request.getParameter("pib");
            final String type = request.getParameter("type");
            final String code = request.getParameter("code");
            final String dateStart = request.getParameter("date1");
            final String dateEnd = request.getParameter("date2");
            final String incomeCode = request.getParameter("incomeCode");
            final String incomeValue = request.getParameter("incomeValue");
            final String outcomeCode = request.getParameter("outcomeCode");
            final String outcomeValue = request.getParameter("outcomeValue");
            final String percentCode = request.getParameter("procentCode");
            final String percentValue = request.getParameter("procentValue");
            final String clearCode = request.getParameter("clearCode");
            final String clearValue = request.getParameter("clearValue");
            ReportStructure file = ReportStructure.builder()
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

            User user = (User) request.getSession().getAttribute("user");
            LocalDateTime time = LocalDateTime.now();

            File gsonFile = new File("C:\\epam_servlet_app\\web\\files\\" + time.toLocalDate() + "-" + time.toLocalTime().getNano() + ".json");
            Report report = Report.builder()
                    .withCreationDate(LocalDateTime.now())
                    .withUser(user)
                    .withFileLink(gsonFile.getAbsolutePath())
                    .withStatus(ReportStatus.NEW)
                    .build();
            reportService.addReportToUser(report, user);

            try (Writer writer = new FileWriter(gsonFile)) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(file, writer);
            } catch (IOException e) {
                throw new ReportFileException("Ur file was corrupted");
            }
        }
        return "/user/create-report.jsp";
    }
}
