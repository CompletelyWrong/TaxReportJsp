package entity.report;

import entity.user.UserEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ReportEntity {
    private final Long id;
    private final UserEntity entityUser;
    private final String fileLink;
    private final LocalDateTime creationDate;
    private final ReportStatus status;

    public ReportEntity(Builder builder) {
        this.id = builder.id;
        this.entityUser = builder.entityUser;
        this.fileLink = builder.fileLink;
        this.creationDate = builder.creationDate;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getEntityUser() {
        return entityUser;
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
        ReportEntity reportEntity = (ReportEntity) o;
        return Objects.equals(id, reportEntity.id) &&
                Objects.equals(entityUser, reportEntity.entityUser) &&
                Objects.equals(fileLink, reportEntity.fileLink) &&
                Objects.equals(creationDate, reportEntity.creationDate) &&
                status == reportEntity.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entityUser, fileLink, creationDate, status);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", user=" + entityUser +
                ", fileLink='" + fileLink + '\'' +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long id;
        private UserEntity entityUser;
        private String fileLink;
        private LocalDateTime creationDate;
        private ReportStatus status;

        public ReportEntity build() {
            return new ReportEntity(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUser(UserEntity entityUser) {
            this.entityUser = entityUser;
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
