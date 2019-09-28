package ru.otus.homework.domain;

import java.util.Map;

public class PersonTest {
    private final Map<String, Question> questionsList;

    public PersonTest(Map<String, Question> questionsList) {
        this.questionsList = questionsList;
    }

    public Map<String, Question> getQuestionsList() {
        return questionsList;
    }

    @Override
    public String toString() {
        StringBuilder testString = new StringBuilder();
        for (Question question : questionsList.values()) {
            testString.append(question);
        }
        return "Test{" +
                testString.toString() +
                '}';
    }
}
