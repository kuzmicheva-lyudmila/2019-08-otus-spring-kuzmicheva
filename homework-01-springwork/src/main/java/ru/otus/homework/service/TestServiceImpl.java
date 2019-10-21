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

    public TestServiceImpl(PersonAnswerDao personAnswerDao) {
        this.personAnswerDao = personAnswerDao;
    }

    @Override
    public PersonAnswer runTest(Person person, PersonTest test, CommunicationDao communicationDao) {
        return personAnswerDao.getPersonAnswer(person, test, communicationDao);
    }

    @Override
    public void getResultTest(PersonAnswer personAnswer, CommunicationDao communicationDao) {
        personAnswerDao.getResultTest(personAnswer, communicationDao);
    }
}
