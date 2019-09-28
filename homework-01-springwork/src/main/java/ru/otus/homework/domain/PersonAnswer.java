package ru.otus.homework.domain;

import java.util.Map;

public class PersonAnswer {
    private final Person person;
    private final PersonTest personTest;
    private final Map<String, Answer> results;
    private int rightAnswerCount;
    private int percentSuccessfulAnswer;

    public PersonAnswer(Person person, PersonTest personTest, Map<String, Answer> results, int rightAnswerCount, int percentSuccessfulAnswer) {
        this.person = person;
        this.personTest = personTest;
        this.results = results;
        this.rightAnswerCount = rightAnswerCount;
        this.percentSuccessfulAnswer = percentSuccessfulAnswer;
    }

    public PersonTest getPersonTest() {
        return personTest;
    }

    public Person getPerson() {
        return person;
    }

    public Map<String, Answer> getResults() {
        return results;
    }

    public boolean getResultTest() {
        boolean resultTest = false;
        if ((rightAnswerCount != 0) && (results.size() != 0)) {
            int percentResultTest = Math.round((float) rightAnswerCount / (float)results.size() * 100);
            resultTest = percentResultTest > percentSuccessfulAnswer;
        }
        return resultTest;
    }
}
