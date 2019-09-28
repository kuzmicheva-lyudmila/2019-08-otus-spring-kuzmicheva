package ru.otus.homework.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class PersonAnswerDaoImpl implements PersonAnswerDao {
    private final int percentSuccessfulAnswer;

    public PersonAnswerDaoImpl(@Value("${percent.successfulAnswers}") int percentSuccessfulAnswer) {
        this.percentSuccessfulAnswer = percentSuccessfulAnswer;
    }

    @Override
    public PersonAnswer getPersonAnswer(Person person, PersonTest test, CommunicationDao communicationDao) {
        Map<String, Answer> results = new HashMap<>();
        int rightAnswerCount = 0;
        for (Map.Entry entry : test.getQuestionsList().entrySet()) {
            Question question = (Question) entry.getValue();
            communicationDao.showMessage(question.getQuestion());
            String answerString = communicationDao.getAnswerMessage("enter.variants", "enter.error", "[\\S]+");
            if (question.getRightAnswer().equals(answerString)) {
                rightAnswerCount++;
            }
            results.put((String) entry.getKey(), new Answer(answerString, question.getRightAnswer().equals(answerString)));
        }
        return new PersonAnswer(person, test, results, rightAnswerCount, percentSuccessfulAnswer);
    }

    @Override
    public void getResultTest(PersonAnswer personAnswer, CommunicationDao communicationDao) {
        Map<String, Answer> results = personAnswer.getResults();

        communicationDao.showMessageWithLocale("show.person", new String[]{": " + personAnswer.getPerson().getFIO()});

        for (Map.Entry entry : personAnswer.getPersonTest().getQuestionsList().entrySet()) {
            String idQuestion = (String) entry.getKey();
            Question question = (Question) entry.getValue();
            Answer answer = results.get(idQuestion);
            communicationDao.showMessage(question.getQuestion());
            communicationDao.showMessageWithLocale("person.answer", new String[]{": " + answer.getVariantOfAnswer()});
            communicationDao.showMessageWithLocale("right.answer", new String[]{": " + question.getRightAnswer()});
            communicationDao.showMessageWithLocale(answer.getResultOfAnswer() ? "question.resultRight" : "question.resultWrong", new String[]{});
        }

        communicationDao.showMessageWithLocale(personAnswer.getResultTest() ? "test.resultSuccess" : "test.resultFailure", new String[]{});
    }
}
