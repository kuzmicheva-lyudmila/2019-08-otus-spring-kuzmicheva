package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PersonAnswerClassTest {
    @Mock
    private Person person;

    @Mock
    private PersonTest personTest;

    private Map<String, Answer> results = setPersonResults();

    @Mock
    private Answer answer;

    private int rightAnswerCount;
    private int percentSuccessfulAnswer;

    @Test
    void getFileds() {
        PersonAnswer personAnswer = new PersonAnswer(person, personTest, results, rightAnswerCount, percentSuccessfulAnswer);
        assertEquals(personAnswer.getPerson(), person);
        assertEquals(personAnswer.getResults(), results);
        assertEquals(personAnswer.getPersonTest(), personTest);
    }

    @Test
    void getResultTest() {
        PersonAnswer successAnswer = new PersonAnswer(person, personTest, results, 4, 70);
        assertTrue(successAnswer.getResultTest());

        PersonAnswer failureAnswer = new PersonAnswer(person, personTest, results, 1, 80);
        assertFalse(failureAnswer.getResultTest());
    }

    private Map<String, Answer> setPersonResults() {
        Map<String, Answer> answerList = new HashMap<>();
        Properties settings = new Properties();
        try {
            settings.load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        int countQuestions = Integer.parseInt(settings.getProperty("count.answers", "5"));
        for (int i = 0; i < countQuestions; i++) {
            answerList.put(String.valueOf(i + 1), answer);
        }
        return answerList;
    }
}