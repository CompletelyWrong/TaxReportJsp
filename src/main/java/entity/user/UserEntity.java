package entity.user;

import java.util.Objects;

public class UserEntity extends AbstractUserEntity {
    private final Integer identificationCode;
    private final InspectorEntity inspectorEntity;

    private UserEntity(UserBuilder builder) {
        super(builder);
        this.identificationCode = builder.identificationCode;
        this.inspectorEntity = builder.inspectorEntity;
    }

    public Integer getIdentificationCode() {
        return identificationCode;
    }

    public InspectorEntity getInspectorEntity() {
        return inspectorEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserEntity that = (UserEntity) o;

        return Objects.equals(identificationCode, that.identificationCode) &&
                Objects.equals(inspectorEntity, that.inspectorEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identificationCode, inspectorEntity);
    }

    @Override
    public String toString() {
        return "User{" + super.toString() +
                ", identificationCode=" + identificationCode +
                '}';
    }

    public static class UserBuilder extends AbstractBuilder<UserBuilder> {
        private Integer identificationCode;
        private InspectorEntity inspectorEntity;

        @Override
        protected UserBuilder self() {
            return this;
        }

        @Override
        public UserEntity build() {
            return new UserEntity(self());
        }

        public UserBuilder withIdentificationCode(Integer identificationCode) {
            this.identificationCode = identificationCode;
            return self();
        }

        public UserBuilder withInspector(InspectorEntity inspectorEntity) {
            this.inspectorEntity = inspectorEntity;
            return self();
        }
    }
}
