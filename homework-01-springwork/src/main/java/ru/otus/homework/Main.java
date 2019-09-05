package ru.otus.homework;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.dao.PersonDao;
import ru.otus.homework.dao.PersonDaoImpl;
import ru.otus.homework.domain.Person;

public class Main {
    public static void main(String[] args) {
 //       ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
 /*       PersonService service = context.getBean(PersonService.class);
        Person ivan = service.getByName("Ivan");
        System.out.println("name: " + ivan.getName() + " age: " + ivan.getAge());*/
        PersonDao personDao = new PersonDaoImpl();
        Person person = personDao.getPerson();
        System.out.println(person.name + " " + person.surname);


    }
}
