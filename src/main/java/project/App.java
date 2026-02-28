package project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import project.config.AppConfig;
import project.consol.ConsoleListener;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(ConsoleListener.class).appWorkig();
    }
}