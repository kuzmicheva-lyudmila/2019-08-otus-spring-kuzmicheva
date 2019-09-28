package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonAnswerDao;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;

@Service
public class TestServiceImpl implements TestService {
    private final PersonAnswerDao personAnswerDao;
    private final CommunicationDao communicationDao;

    public TestServiceImpl(PersonAnswerDao personAnswerDao, CommunicationDao communicationDao) {
        this.personAnswerDao = personAnswerDao;
        this.communicationDao = communicationDao;
    }

    @Override
    public PersonAnswer runTest(Person person, PersonTest test) {
        PersonAnswer personAnswer = personAnswerDao.getPersonAnswer(person, test, communicationDao);
        return personAnswer;
    }

    @Override
    public void getResultTest(PersonAnswer personAnswer) {
        personAnswerDao.getResultTest(personAnswer, communicationDao);
    }
}
