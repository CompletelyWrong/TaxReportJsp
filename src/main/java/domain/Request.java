package domain;

import java.util.Objects;

public class Request {
    private Long id;
    private final User user;
    private final String reason;

    public Request(Long id, User user, String reason) {
        this.id = id;
        this.user = user;
        this.reason = reason;
    }

    public Request(User user, String reason) {
        this.user = user;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(user, request.user) &&
                Objects.equals(reason, request.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, reason);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", entityUser=" + user +
                ", reason='" + reason + '\'' +
                '}';
    }
}
