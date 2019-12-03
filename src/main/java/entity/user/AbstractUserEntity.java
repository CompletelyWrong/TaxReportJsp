package entity.user;

import java.util.Objects;

public abstract class AbstractUserEntity {
    private final Long id;
    private final String email;
    private final String password;
    private final String name;
    private final String surname;
    private final String patronymic;
    private final Role role;

    AbstractUserEntity(AbstractBuilder<? extends AbstractBuilder> abstractBuilder) {
        this.id = abstractBuilder.id;
        this.email = abstractBuilder.email;
        this.password = abstractBuilder.password;
        this.name = abstractBuilder.name;
        this.surname = abstractBuilder.surname;
        this.patronymic = abstractBuilder.patronymic;
        this.role = abstractBuilder.role;
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
        AbstractUserEntity that = (AbstractUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(patronymic, that.patronymic) &&
                role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, surname, patronymic, role);
    }

    @Override
    public String toString() {
        return "AbstractUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", role=" + role +
                '}';
    }

    public static class AbstractBuilder<SELF extends AbstractBuilder<SELF>> {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String surname;
        private String patronymic;
        private Role role;

        AbstractBuilder() {

        }

        @SuppressWarnings("unchecked")
        protected SELF self() {
            return (SELF) this;
        }

        public AbstractUserEntity build() {
            return null;
        }

        public SELF withId(Long id) {
            this.id = id;
            return self();
        }

        public SELF withEmail(String email) {
            this.email = email;
            return self();
        }

        public SELF withPassword(String password) {
            this.password = password;
            return self();
        }

        public SELF withName(String name) {
            this.name = name;
            return self();
        }

        public SELF withSurname(String surname) {
            this.surname = surname;
            return self();
        }

        public SELF withPatronymic(String patronymic) {
            this.patronymic = patronymic;
            return self();
        }

        public SELF withRole(Role role) {
            this.role = role;
            return self();
        }
    }
}
