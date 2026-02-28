package project.model;

import java.util.List;

//
public class User {
    private final int id;
    private final String login;
    private List<Account> accountList;

    public User(int id, String login, List<Account> accountList) {
        this.id = id;
        this.login = login;
        this.accountList = accountList;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    @Override
    public String toString() {
        String string = "User: " + "id=" + id + ", login=" + login + ". Accounts:\n";
        for (Account account : accountList) {
            string += account;
            string += "\n";
        }
        return string;
    }
}
