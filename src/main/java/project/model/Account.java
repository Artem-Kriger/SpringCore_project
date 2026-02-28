package project.model;

public class Account {
    private final int accountId;
    private final int userId;
    private float moneyAmount;

    public Account(int accountId, int userId, int moneyAmount) {
        this.accountId = accountId;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    // Getters
    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public float getMoneyAmount() {
        return moneyAmount;
    }

    // Setter
    public void setMoneyAmount(float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Account{accountId=" + accountId + ", userId=" + userId + ", moneyAmount=" + moneyAmount + "}";
    }

}
