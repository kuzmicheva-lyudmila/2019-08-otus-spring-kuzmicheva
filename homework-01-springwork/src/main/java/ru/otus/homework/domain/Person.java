package ru.otus.homework.domain;

public class Person {
    public final String surname;
    public final String name;

    public Person(String surname, String name) {
        this.surname = surname;
        this.name = name;
    }

    public String getFIO() {
        StringBuilder fio = new StringBuilder(surname);
        fio.append(' ');
        fio.append(name);
        return fio.toString();
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
