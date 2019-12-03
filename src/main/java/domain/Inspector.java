package domain;

import entity.user.Role;

import java.util.Objects;

public class Inspector {
    private final Long id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final Role role;

    public Inspector(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.role = builder.role;
    }

    public Inspector(Inspector inspector, String password) {
        this.id = inspector.id;
        this.email = inspector.email;
        this.password = password;
        this.name = inspector.name;
        this.surname = inspector.surname;
        this.patronymic = inspector.patronymic;
        this.role = inspector.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Inspector inspector = (Inspector) o;
        return Objects.equals(id, inspector.id) &&
                Objects.equals(email, inspector.email) &&
                Objects.equals(password, inspector.password) &&
                Objects.equals(name, inspector.name) &&
                Objects.equals(surname, inspector.surname) &&
                Objects.equals(patronymic, inspector.patronymic) &&
                role == inspector.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, patronymic, role);
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String patronymic;
        private Role role;

        public Builder() {
        }

        public Inspector build() {
            return new Inspector(this);
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder withPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }
    }
}
