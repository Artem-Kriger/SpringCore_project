package project;


public class AccountProperties {
    private final int defaultAmount;
    private final float defaultCommission;

    public AccountProperties(int defaultAmount, float defaultCommission) {
        this.defaultAmount = defaultAmount;
        this.defaultCommission = defaultCommission;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public float getDefaultCommission() {
        return defaultCommission;
    }
}
