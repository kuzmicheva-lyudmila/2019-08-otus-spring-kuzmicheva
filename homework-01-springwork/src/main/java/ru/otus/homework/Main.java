package ru.otus.homework;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;
import ru.otus.homework.service.AuthorizationService;
import ru.otus.homework.service.LoadTestService;
import ru.otus.homework.service.TestService;
import ru.otus.homework.service.TestServiceImpl;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        AuthorizationService authorizationService = context.getBean(AuthorizationService.class);
        Person user = authorizationService.getPerson();
        LoadTestService loadTestService = context.getBean(LoadTestService.class);
        PersonTest test = loadTestService.getTest();
        TestService testService = context.getBean(TestServiceImpl.class);
        PersonAnswer personAnswer = testService.runTest(user, test);
        testService.getResultTest(personAnswer);
    }
}
