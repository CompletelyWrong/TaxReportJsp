package service.mapper;

import domain.Action;
import domain.Inspector;
import domain.Report;
import entity.action.ActionEntity;
import entity.report.ReportEntity;
import entity.user.InspectorEntity;

public class ActionMapper {
    public ActionEntity mapActionToActionEntity(Action action, Report report) {
        return ActionEntity.builder()
                .withActionType(action.getAction())
                .withDate(action.getDateTime())
                .withId(action.getId())
                .withInspector(new InspectorEntity.InspectorBuilder()
                        .withId(action.getInspector().getId())
                        .build())
                .withMessage(action.getMessage())
                .withReport(ReportEntity.builder()
                        .withId(report.getId())
                        .build())
                .build();
    }

    public Action mapActionEntityToAction(ActionEntity entity) {
        return Action.builder()
                .withActionType(entity.getAction())
                .withDate(entity.getDateTime())
                .withId(entity.getId())
                .withInspector(Inspector.builder()
                        .withId(entity.getInspectorEntity().getId())
                        .build())
                .withMessage(entity.getMessage())
                .withReport(Report.builder()
                        .withId(entity.getReportEntity().getId())
                        .build())
                .build();
    }
}
