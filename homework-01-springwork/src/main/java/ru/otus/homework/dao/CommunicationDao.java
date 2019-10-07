package ru.otus.homework.dao;

import ru.otus.homework.domain.Question;

import java.util.Map;

public interface CommunicationDao {
    String getAnswerMessage(String questionString, String errorString, String template);
    Map<String, Question> readTestFromCSVFile(int countRows, int countAnswerVariants, String filename);
    void showMessage(String messageString);
    void showMessageWithLocale(String messageString, String[] stringParameters);
}
