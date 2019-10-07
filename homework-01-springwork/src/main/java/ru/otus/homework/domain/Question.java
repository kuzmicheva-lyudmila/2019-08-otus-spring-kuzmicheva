package ru.otus.homework.domain;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Question {
    private final String id;
    private final String textQuestion;
    private final List<String> answerVariants;

    private final String rightAnswer;

    public Question(String id, String textQuestion, String rightAnswer, List<String> answerVariants) {
        this.id = id;
        this.textQuestion = textQuestion;
        this.answerVariants = answerVariants;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        StringBuilder sb = new StringBuilder(id);
        sb.append('\t');
        sb.append(textQuestion);
        sb.append('\n');
        for (String answerVariant: answerVariants) {
            sb.append(answerVariant);
            sb.append('\n');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", textQuestion='" + textQuestion + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", answerVariant=" + Arrays.toString(answerVariants.toArray()) +
                '}';
    }
}
