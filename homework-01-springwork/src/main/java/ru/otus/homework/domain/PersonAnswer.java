package ru.otus.homework.domain;

import java.util.Map;

public class PersonAnswer {
    private final Person person;
    private final Map<Question, Answer> results;
    private int rightAnswerCount;

    public PersonAnswer(Person person, Map<Question, Answer> results) {
        this.person = person;
        this.results = results;
    }

    public String getResult() {
        return null;
    }
}
