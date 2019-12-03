package entity.user;

public class InspectorEntity extends AbstractUserEntity {

    public InspectorEntity(InspectorBuilder builder) {
        super(builder);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public String getPatronymic() {
        return super.getPatronymic();
    }

    @Override
    public String toString() {
        return "Inspector{" + super.toString() +
                "photoLink='" + '\'' +
                '}';
    }

    public static class InspectorBuilder extends AbstractBuilder<InspectorBuilder> {

        @Override
        protected InspectorBuilder self() {
            return this;
        }

        @Override
        public InspectorEntity build() {
            return new InspectorEntity(self());
        }

    }


}
