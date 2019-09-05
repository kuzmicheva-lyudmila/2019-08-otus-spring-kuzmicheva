package ru.otus.homework.dao;

import ru.otus.homework.domain.Person;

import java.util.Scanner;

public class PersonDaoImpl implements PersonDao {
    private final Person person;

    public PersonDaoImpl() {
        this.person = setPerson();
    }

    @Override
    public Person getPerson() {
        return null;
    }

    private String getUserInputString(Scanner sc, String message) {
        System.out.println(message);
        while (!sc.hasNext("^[a-zA-Z]+")) {
            System.out.println("That not a valid string!");
            sc.next();
        }
        return sc.next();
    }

    private Person setPerson() {
        Scanner sc = new Scanner(System.in);
        String name = getUserInputString(sc, "Please enter a surname: ");
        String surname = getUserInputString(sc, "Please enter a name: ");
        return new Person(surname, name);
    }
}
