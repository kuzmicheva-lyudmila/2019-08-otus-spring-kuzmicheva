package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.homework.service.TestService;

@SpringBootApplication
public class MainClass {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainClass.class, args);
        TestService testService = context.getBean(TestService.class);
        testService.runTest();
    }
}
