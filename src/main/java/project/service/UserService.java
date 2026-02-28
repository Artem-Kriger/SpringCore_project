package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.checkers.UserValidator;
import project.exceptions.DuplicateLoginException;
import project.exceptions.NoUsersException;
import project.model.Account;
import project.model.User;

import java.util.*;

@Component
public class UserService {
    private Scanner scanner;
    private AccountService accountService;

    private Map<Integer, User> userMap = new HashMap<>();
    private int userId = 1;

    @Autowired
    public UserService(Scanner scanner, AccountService accountService) {
        this.scanner = scanner;
        this.accountService = accountService;
    }

    public void createUser() {
        System.out.print("INTER LOGIN:");
        String login = scanner.nextLine();
        try {
            if (UserValidator.validateUser(this.userMap, login)) { // Проверка на наличие пользователя
                throw new DuplicateLoginException(); // Бросаем исключение, что пользователь уже есть
            }
            List<Account> accSpis = new ArrayList<>();
            User user = new User(this.userId, login, accSpis);

            this.userMap.put(userId, user);
            accountService.createAccount(this.userMap, this.userId);
            this.userId++;

            System.out.println("USER CREATED.\n");
        } catch (DuplicateLoginException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showAllUsers() {
        try {
            if (!this.userMap.isEmpty()) {
                for (User user : userMap.values()) {
                    System.out.println(user);
                }
            } else {
                throw new NoUsersException(); // Бросаем исключение если нет пользователей
            }
        } catch (NoUsersException e) {
            System.out.println(e.getMessage());
        }
    }

    // Getters
    public Map<Integer, User> getUserMap() {
        return this.userMap;
    }

    public int getUserId() {
        return userId;
    }
}
