package domain;

import entity.user.Role;

import java.util.Objects;

public class User {
    private final Long id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final Role role;
    private final Integer identificationCode;

    public User(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.patronymic = builder.patronymic;
        this.role = builder.role;
        this.identificationCode = builder.identificationCode;
    }

    public User(User user, String password) {
        this.id = user.id;
        this.email = user.email;
        this.password = password;
        this.name = user.name;
        this.surname = user.surname;
        this.patronymic = user.patronymic;
        this.role = user.role;
        this.identificationCode = user.identificationCode;
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

    public Integer getIdentificationCode() {
        return identificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(patronymic, user.patronymic) &&
                role == user.role &&
                Objects.equals(identificationCode, user.identificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, patronymic, role, identificationCode);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                ", identificationCode=" + identificationCode +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String patronymic;
        private Role role;
        private Integer identificationCode;

        Builder() {
        }

        public User build() {
            return new User(this);
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

        public Builder withIdentificationCode(Integer identificationCode) {
            this.identificationCode = identificationCode;
            return this;
        }
    }
}
