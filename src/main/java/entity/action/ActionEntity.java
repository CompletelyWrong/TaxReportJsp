package entity.action;

import entity.report.ReportEntity;
import entity.user.InspectorEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ActionEntity {
    private final Long id;
    private final InspectorEntity inspectorEntity;
    private final ReportEntity reportEntity;
    private final LocalDateTime dateTime;
    private final String message;
    private final ActionType action;

    public ActionEntity(Builder builder) {
        this.id = builder.id;
        this.inspectorEntity = builder.inspectorEntity;
        this.reportEntity = builder.reportEntity;
        this.dateTime = builder.dateTime;
        this.message = builder.message;
        this.action = builder.action;
    }

    public Long getId() {
        return id;
    }

    public InspectorEntity getInspectorEntity() {
        return inspectorEntity;
    }

    public ReportEntity getReportEntity() {
        return reportEntity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public ActionType getAction() {
        return action;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ActionEntity actionEntity1 = (ActionEntity) o;
        return Objects.equals(id, actionEntity1.id) &&
                Objects.equals(inspectorEntity, actionEntity1.inspectorEntity) &&
                Objects.equals(reportEntity, actionEntity1.reportEntity) &&
                Objects.equals(dateTime, actionEntity1.dateTime) &&
                Objects.equals(message, actionEntity1.message) &&
                action == actionEntity1.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inspectorEntity, reportEntity, dateTime, message, action);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", inspector=" + inspectorEntity +
                ", report=" + reportEntity +
                ", date=" + dateTime +
                ", message='" + message + '\'' +
                ", action=" + action +
                '}';
    }

    public static class Builder {
        private Long id;
        private InspectorEntity inspectorEntity;
        private ReportEntity reportEntity;
        private LocalDateTime dateTime;
        private String message;
        private ActionType action;


        public ActionEntity build() {
            return new ActionEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withInspector(InspectorEntity inspectorEntity) {
            this.inspectorEntity = inspectorEntity;
            return this;
        }

        public Builder withReport(ReportEntity reportEntity) {
            this.reportEntity = reportEntity;
            return this;
        }

        public Builder withDate(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder withActionType(ActionType action) {
            this.action = action;
            return this;
        }
    }
}
