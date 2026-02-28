package project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import project.AccountProperties;

import java.util.Scanner;

@Configuration
@ComponentScan("project")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public AccountProperties accountProperties(
            @Value("${account.default-amount:500}") int defaultAmount,
            @Value("${account.transfer-commission:0.02}") float defaultCommission
    ) {
        return new AccountProperties(defaultAmount, defaultCommission);
    }

}
