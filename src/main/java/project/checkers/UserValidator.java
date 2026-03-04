package project.checkers;

import project.model.User;

import java.util.Map;

public class UserValidator {

    // Проверка на наличие пользователя по логину
    public static boolean validateUser(Map<Integer, User> userMap, String login) {
        for (User user : userMap.values()) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }
    // Проверка на наличие пользователя по Id
    public static boolean validateUser(Map<Integer, User> userMap, int userId) {
        if (userMap.containsKey(userId)) {
            return true;
        }
        return false;
    }
}
