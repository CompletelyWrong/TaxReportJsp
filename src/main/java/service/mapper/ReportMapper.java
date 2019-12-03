package service.mapper;

import domain.Report;
import domain.User;
import entity.report.ReportEntity;
import entity.user.UserEntity;

public class ReportMapper {
    public ReportEntity mapReportToReportEntity(Report report, User user) {
        return ReportEntity.builder()
                .withCreationDate(report.getCreationDate())
                .withFileLink(report.getFileLink())
                .withId(report.getId())
                .withStatus(report.getStatus())
                .withUser(new UserEntity.UserBuilder()
                        .withId(user.getId())
                        .build())
                .build();
    }

    public ReportEntity mapReportToReportEntity(Report report, Long reportId) {
        return ReportEntity.builder()
                .withCreationDate(report.getCreationDate())
                .withFileLink(report.getFileLink())
                .withId(reportId)
                .withStatus(report.getStatus())
                .withUser(new UserEntity.UserBuilder()
                        .withId(report.getUser().getId())
                        .build())
                .build();
    }

    public Report mapReportEntityToReport(ReportEntity entity) {
        return Report.builder()
                .withCreationDate(entity.getCreationDate())
                .withFileLink(entity.getFileLink())
                .withId(entity.getId())
                .withStatus(entity.getStatus())
                .withUser(User.builder()
                        .withId(entity.getEntityUser().getId())
                        .build())
                .build();
    }
}
