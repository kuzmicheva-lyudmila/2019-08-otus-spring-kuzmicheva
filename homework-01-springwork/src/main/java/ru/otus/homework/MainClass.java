package ru.otus.homework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.homework.service.TestService;

public class MainClass {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        TestService testService = context.getBean(TestService.class);
        testService.runTest();
    }
}
