package command.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import command.Command;
import domain.Report;
import domain.ReportStructure;
import entity.report.ReportStatus;
import exception.ReportFileException;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Objects;

public class UpdateReport implements Command {
    private final ReportService reportService;

    public UpdateReport(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("report_id"));
        Report report = reportService.findById(id);

        try (Reader reader = new FileReader(report.getFileLink())) {
            ReportStructure structure = new Gson().fromJson(reader, ReportStructure.class);
            request.setAttribute("reportStructure", structure);
            request.setAttribute("report_id", report.getId());
        } catch (IOException e) {
            throw new ReportFileException("Ur file was corrupted");
        }

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

            File gsonFile = new File(report.getFileLink());
            Report updaterReport = Report.builder(report)
                    .withFileLink(gsonFile.getAbsolutePath())
                    .withStatus(ReportStatus.UPDATED)
                    .build();
            reportService.updateReportById(updaterReport, report.getId());

            try (Writer writer = new FileWriter(gsonFile)) {
                Gson gson = new GsonBuilder().create();
                gson.toJson(file, writer);
            } catch (IOException e) {
                throw new ReportFileException("Ur file was corrupted");
            }
            return "/user?command=report&report_id=" + report.getId();
        }
        return "/user/update-report.jsp";
    }
}
