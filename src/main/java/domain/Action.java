package domain;

import entity.action.ActionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Action {
    private final Long id;
    private final Inspector inspector;
    private final Report report;
    private final LocalDateTime dateTime;
    private final String message;
    private final ActionType action;

    public Action(Builder builder) {
        this.id = builder.id;
        this.inspector = builder.inspector;
        this.report = builder.report;
        this.dateTime = builder.dateTime;
        this.message = builder.message;
        this.action = builder.action;
    }

    public Long getId() {
        return id;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public Report getReport() {
        return report;
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
        Action action1 = (Action) o;
        return Objects.equals(id, action1.id) &&
                Objects.equals(inspector, action1.inspector) &&
                Objects.equals(report, action1.report) &&
                Objects.equals(dateTime, action1.dateTime) &&
                Objects.equals(message, action1.message) &&
                action == action1.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inspector, report, dateTime, message, action);
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", inspector=" + inspector +
                ", report=" + report +
                ", dateTime=" + dateTime +
                ", message='" + message + '\'' +
                ", action=" + action +
                '}';
    }

    public static class Builder {
        private Long id;
        private Inspector inspector;
        private Report report;
        private LocalDateTime dateTime;
        private String message;
        private ActionType action;


        public Action build() {
            return new Action(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withInspector(Inspector inspector) {
            this.inspector = inspector;
            return this;
        }

        public Builder withReport(Report report) {
            this.report = report;
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
