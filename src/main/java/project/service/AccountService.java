package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.AccountProperties;
import project.checkers.AccountValidator;
import project.checkers.MoneyAmountValidator;
import project.checkers.UserValidator;
import project.exceptions.*;
import project.model.Account;
import project.model.User;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

@Component
public class AccountService {
    private Scanner scanner;
    private Map<Integer, Account> accountMap = new HashMap<>();
    private int accountId = 1;
    private AccountProperties accountProperties;

    @Autowired
    public AccountService(AccountProperties accountProperties, Scanner scanner) {
        this.accountProperties = accountProperties;
        this.scanner = scanner;
    }

    // Дальше 2 перегруженных метода создания юзера
    public void createAccount(Map<Integer, User> userMap, int userId) {
        Account account = new Account(this.accountId, userId, accountProperties.getDefaultAmount());
        this.accountMap.put(this.accountId, account);
        this.accountId++;
        userMap.get(userId).getAccountList().add(account);
    }

    public void createAccount(Map<Integer, User> userMap) {
        try {
            if (userMap.isEmpty()) { // Проверка на наличие пользователей
                throw new NoUsersException();
            }

            System.out.print("INTER UserId:");
            int userId;
            try { // Проверка на правильный ввод
                userId = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new InvalidInputException();
            } finally {
                scanner.nextLine();
            }

            if (!UserValidator.validateUser(userMap, userId)) { // Проверка на наличие пользователя
                throw new UserNotFoundException();
            }
            Account account = new Account(this.accountId, userId, accountProperties.getDefaultAmount());
            this.accountMap.put(this.accountId, account);
            this.accountId++;
            userMap.get(userId).getAccountList().add(account);

            System.out.println("ACCOUNT CREATED.\n");
        } catch (UserNotFoundException | NoUsersException | InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public void accountDepositing(Map<Integer, User> userMap) {
        try {
            if (userMap.isEmpty()) { // Проверка на наличие пользователей
                throw new NoUsersException();
            }
            int accId = 0;
            float money = 0;
            try { // Проверка на правильный ввод
                System.out.print("INTER AccountId:");
                accId = scanner.nextInt();
                if (!AccountValidator.validateAccount(this.accountMap, accId)) { // Проверка на наличие аккаунта
                    throw new AccountNotFoundException();
                }
                System.out.print("INTER Money:");
                money = scanner.nextFloat();
                if (money <= 0) {
                    throw new MoneyNotPositiveException();
                }

                Account account = this.accountMap.get(accId);
                float currentMoney = account.getMoneyAmount();
                account.setMoneyAmount(currentMoney + money);
                System.out.println("ACCOUNT DEPOSITING COMPLITED.\n");

            } catch (InputMismatchException e) {
                throw new InvalidInputException();
            } catch (AccountNotFoundException | MoneyNotPositiveException | NoUsersException e) {
                throw e;
            } finally {
                scanner.nextLine();
            }
        } catch (AccountNotFoundException | InvalidInputException | NoUsersException e) {
            System.out.println(e.getMessage());
        }
    }

    public void accountWithdrawing(Map<Integer, User> userMap) {
        try {
            if (userMap.isEmpty()) { // Проверка на наличие пользователей
                throw new NoUsersException();
            }
            int accId = 0;
            float money = 0;
            try { // Проверка на правильный ввод
                System.out.print("INTER AccountId:");
                accId = scanner.nextInt();
                if (!AccountValidator.validateAccount(this.accountMap, accId)) { // Проверка на наличие аккаунта
                    throw new AccountNotFoundException();
                }
                System.out.print("INTER Money:");
                money = scanner.nextFloat();
                if (money <= 0) {
                    throw new MoneyNotPositiveException();
                }
                if (!MoneyAmountValidator.validateMoneyAmount(this.accountMap, accId, money)) { // Проверка: Хватает ли средств
                    throw new NotEnoughFundsException();
                }

                Account account = this.accountMap.get(accId);
                float currentMoney = account.getMoneyAmount();
                account.setMoneyAmount(currentMoney - money);
                System.out.println("ACCOUNT WITHDRAWING COMPLITED.\n");

            } catch (InputMismatchException e) {
                throw new InvalidInputException();
            } catch (AccountNotFoundException | NotEnoughFundsException | MoneyNotPositiveException | NoUsersException e) {
                throw e;
            } finally {
                scanner.nextLine();
            }
        } catch (AccountNotFoundException | InvalidInputException | NotEnoughFundsException | NoUsersException e) {
            System.out.println(e.getMessage());
        }
    }

    public void accountTransfering(Map<Integer, User> userMap) {
        try {
            if (userMap.isEmpty()) { // Проверка на наличие пользователей
                throw new NoUsersException();
            }
            int fromAccId = 0;
            int toAccId = 0;
            float money = 0;
            try { // Проверка на правильный ввод
                System.out.print("INTER Sender AccountId:");
                fromAccId = scanner.nextInt();
                if (!AccountValidator.validateAccount(this.accountMap, fromAccId)) { // Проверка на наличие аккаунта
                    throw new AccountNotFoundException();
                }
                System.out.print("INTER Recipient AccountId:");
                toAccId = scanner.nextInt();
                if (!AccountValidator.validateAccount(this.accountMap, toAccId)) { // Проверка на наличие аккаунта
                    throw new AccountNotFoundException();
                }
                if (fromAccId == toAccId) {
                    throw new TransferToMeException();
                }
                System.out.print("INTER Money:");
                money = scanner.nextFloat();
                if (money <= 0) {
                    throw new MoneyNotPositiveException();
                }
                if (!MoneyAmountValidator.validateMoneyAmount(this.accountMap, fromAccId, money)) { // Проверка: Хватает ли средств
                    throw new NotEnoughFundsException();
                }

                Account accountSender = this.accountMap.get(fromAccId);
                float currentMoneySender = accountSender.getMoneyAmount();
                accountSender.setMoneyAmount(currentMoneySender - money);

                Account accountRecipient = this.accountMap.get(toAccId);

                float currentMoneyRecipient = accountRecipient.getMoneyAmount();
                float amountAfterCommission = 0;

                if (accountSender.getUserId() != accountRecipient.getUserId()) {
                    amountAfterCommission = money * (1 - accountProperties.getDefaultCommission());
                }
                accountRecipient.setMoneyAmount(currentMoneyRecipient + amountAfterCommission);
                System.out.println("ACCOUNT TRANSFERING COMPLITED.\n");

            } catch (InputMismatchException e) {
                throw new InvalidInputException();
            } catch (AccountNotFoundException | NotEnoughFundsException | MoneyNotPositiveException |
                     TransferToMeException | NoUsersException e) {
                throw e;
            } finally {
                scanner.nextLine();
            }
        } catch (AccountNotFoundException | InvalidInputException | NotEnoughFundsException |
                 MoneyNotPositiveException |
                 TransferToMeException | NoUsersException e) {
            System.out.println(e.getMessage());
        }
    }

    public void accountClosing(Map<Integer, User> userMap) {
        try {
            if (userMap.isEmpty()) { // Проверка на наличие пользователей
                throw new NoUsersException();
            }
            int accId = 0;
            try { // Проверка на правильный ввод
                System.out.print("INTER AccountId:");
                accId = scanner.nextInt();
                if (!AccountValidator.validateAccount(this.accountMap, accId)) { // Проверка на наличие аккаунта
                    throw new AccountNotFoundException();
                }
                if (!AccountValidator.validateLastAccount(this.accountMap, userMap, accId)) { // Проверка на последний аккаунт
                    throw new LastAccountException();
                }

                Account accountClosing = this.accountMap.get(accId);
                float currentMoney = accountClosing.getMoneyAmount();

                int userId = accountClosing.getUserId();
                Account firstAccount = userMap.get(userId).getAccountList().getFirst();
                float firstAccountMoney = firstAccount.getMoneyAmount();

                firstAccount.setMoneyAmount(firstAccountMoney + currentMoney);

                userMap.get(userId).getAccountList().remove(accountClosing);
                this.accountMap.remove(accId);

                System.out.println("ACCOUNT CLOSING COMPLITED.\n");

            } catch (InputMismatchException e) {
                throw new InvalidInputException();
            } catch (AccountNotFoundException | LastAccountException | NoUsersException e) {
                throw e;
            } finally {
                scanner.nextLine();
            }
        } catch (AccountNotFoundException | InvalidInputException | LastAccountException | NoUsersException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getters
    public Map<Integer, Account> getAccountMap() {
        return accountMap;
    }

    public int getAccountId() {
        return accountId;
    }
}