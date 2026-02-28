package project.checkers;

import project.model.Account;
import project.model.User;

import java.util.Map;


public class AccountValidator {
    // Проверка на наличие аккаунта
    public static boolean validateAccount(Map<Integer, Account> accountMap, int accountId) {
        return accountMap.containsKey(accountId);
    }

    // Проверка на последний аккаунт (Для функции закрытия счета)
    public static boolean validateLastAccount(Map<Integer, Account> accountMap, Map<Integer, User> userMap, int accId) {
        int userId = accountMap.get(accId).getUserId();
        return userMap.get(userId).getAccountList().size() > 1;
    }
}
