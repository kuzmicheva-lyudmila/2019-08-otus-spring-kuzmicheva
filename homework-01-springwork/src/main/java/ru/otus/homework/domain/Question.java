package ru.otus.homework.domain;

import java.util.List;

public class Question {
    private final int id;
    private final String textQuestion;
    private final List<String> answerVariant;
    private final int rightAnswer;

    public Question(int id, String textQuestion, List<String> answerVariant, int rightAnswer) {
        this.id = id;
        this.textQuestion = textQuestion;
        this.answerVariant = answerVariant;
        this.rightAnswer = rightAnswer;
    }
}
