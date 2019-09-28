package ru.otus.homework.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.PersonTest;

import java.util.Map;

@Service
public class TestDaoImpl implements TestDao{
    private final int MAX_COUNT_QUESTIONS = 10;
    private final int MAX_COUNT_ANSWER_VARIANTS = 5;
    private final String fileName;
    private final int countQuestions;
    private final int countAnswerVariants;

    public TestDaoImpl(@Value("${test.filename}") String fileName, @Value("${count.answers}") int countQuestions, @Value("${count.variants}") int countAnswerVariants) {
        this.fileName = fileName;
        this.countQuestions = countQuestions;
        this.countAnswerVariants = countAnswerVariants;
    }

    @Override
    public PersonTest getTest(CommunicationDao communicationDao) {
        Map<String, Question> questionsList = communicationDao.readTestFromCSVFile(Math.min(countQuestions, MAX_COUNT_QUESTIONS), Math.min(countAnswerVariants, MAX_COUNT_ANSWER_VARIANTS), fileName);
        return new PersonTest(questionsList);
    }
}
