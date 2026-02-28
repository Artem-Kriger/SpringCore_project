package project.consol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.AccountProperties;
import project.service.AccountService;
import project.service.UserService;

import java.util.Scanner;


@Component
public class ConsoleListener {
    private UserService userService;
    private AccountService accountService;
    private Scanner scanner;


    @Autowired
    public ConsoleListener(Scanner scanner, AccountProperties accountProperties) {
        this.scanner = scanner;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    public void appWorkig() {
        System.out.print("MiniBank started. Type EXIT to stop.");
        while (true) {
            System.out.print(
                    "Available commands: USER_CREATE, SHOW_ALL_USERS, ACCOUNT_CREATE\n" +
                            "ACCOUNT_DEPOSIT, ACCOUNT_WITHDRAW, ACCOUNT_TRANSFER, ACCOUNT_CLOSE, EXIT\n\nEnter command:"
            );
            String command = scanner.nextLine();
            if (command.equals("USER_CREATE")) {
                this.userService.createUser();

            } else if (command.equals("SHOW_ALL_USERS")) {
                this.userService.showAllUsers();

            } else if (command.equals("ACCOUNT_CREATE")) {
                this.accountService.createAccount(userService.getUserMap());

            } else if (command.equals("ACCOUNT_DEPOSIT")) {
                this.accountService.accountDepositing(userService.getUserMap());

            } else if (command.equals("ACCOUNT_WITHDRAW")) {
                this.accountService.accountWithdrawing(userService.getUserMap());

            } else if (command.equals("ACCOUNT_TRANSFER")) {
                this.accountService.accountTransfering(userService.getUserMap());

            } else if (command.equals("ACCOUNT_CLOSE")) {
                this.accountService.accountClosing(userService.getUserMap());

            } else if (command.equals("EXIT")) {
                System.out.println("APPLICATION EXITED.");
                break;
            } else {
                System.out.println("Error: INVALID COMMAND\n");
            }
        }
    }
}
