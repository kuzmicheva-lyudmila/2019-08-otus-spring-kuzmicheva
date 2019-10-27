package ru.otus.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.Console;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
        //SpringApplication.run(MainApplication.class, args);
        ApplicationContext context = run(MainApplication.class);
        PersonDao dao = context.getBean(PersonDao.class);

        System.out.println("All count " + dao.count());

        dao.insert(new Person(2, "ivan"));

        System.out.println("All count " + dao.count());

        Person ivan = dao.getById(2);

        System.out.println("Ivan id: " + ivan.getId() + " name: " + ivan.getName());

        Console.main(args);
    }
}