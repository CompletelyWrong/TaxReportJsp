package domain;

import entity.report.ReportStatus;

import java.time.LocalDateTime;
import java.util.Objects;

public class Report {
    private final Long id;
    private final User user;
    private final String fileLink;
    private final LocalDateTime creationDate;
    private final ReportStatus status;

    public Report(Builder builder) {
        this.id = builder.id;
        this.user = builder.user;
        this.fileLink = builder.fileLink;
        this.creationDate = builder.creationDate;
        this.status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Report report) {
        return new Builder(report);
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFileLink() {
        return fileLink;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Report report = (Report) o;
        return Objects.equals(id, report.id) &&
                Objects.equals(user, report.user) &&
                Objects.equals(fileLink, report.fileLink) &&
                Objects.equals(creationDate, report.creationDate) &&
                status == report.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, fileLink, creationDate, status);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", user=" + user +
                ", fileLink='" + fileLink + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long id;
        private User user;
        private String fileLink;
        private LocalDateTime creationDate;
        private ReportStatus status;

        private Builder(Report report) {
            this.id = report.id;
            this.user = report.user;
            this.fileLink = report.fileLink;
            this.creationDate = report.creationDate;
            this.status = report.status;
        }

        private Builder() {

        }

        public Report build() {
            return new Report(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withFileLink(String fileLink) {
            this.fileLink = fileLink;
            return this;
        }

        public Builder withCreationDate(LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withStatus(ReportStatus status) {
            this.status = status;
            return this;
        }
    }
}
