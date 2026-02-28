package project.checkers;

import project.model.Account;

import java.util.Map;

public class MoneyAmountValidator {

    // Проверка: Хватает ли средств
    public static boolean validateMoneyAmount(Map<Integer, Account> accountMap, int accId, float money) {
        float currentMoney = accountMap.get(accId).getMoneyAmount();
        if (currentMoney >= money) {
            return true;
        }
        return false;
    }
}
