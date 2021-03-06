package entity.user;

public enum Role {
    INSPECTOR(2), ADMIN(1), LEGAL_TAXPAYER(3), INDIVIDUAL_TAXPAYER(4);
    private final int roleIndex;

    Role(int index) {
        this.roleIndex = index;
    }

    public int getRoleIndex() {
        return roleIndex;
    }
}
