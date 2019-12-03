package entity.request;

import entity.user.UserEntity;

import java.util.Objects;

public class RequestEntity {
    private final Long id;
    private final UserEntity entityUser;
    private final String reason;

    public RequestEntity(Long id, UserEntity entityUser, String reason) {
        this.id = id;
        this.entityUser = entityUser;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getEntityUser() {
        return entityUser;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestEntity that = (RequestEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(entityUser, that.entityUser) &&
                Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, entityUser, reason);
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + id +
                ", entityUser=" + entityUser +
                ", reason='" + reason + '\'' +
                '}';
    }
}
