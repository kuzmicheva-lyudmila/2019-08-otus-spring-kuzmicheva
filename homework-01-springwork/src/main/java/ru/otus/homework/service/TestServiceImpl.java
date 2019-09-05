package ru.otus.homework.service;

import ru.otus.homework.dao.PersonAnswerDao;
import ru.otus.homework.dao.PersonDao;
import ru.otus.homework.dao.TestDao;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.Test;

public class TestServiceImpl implements TestService {

    private final TestDao testDao;
    private final PersonDao personDao;
    private final PersonAnswerDao personAnswerDao;

    public TestServiceImpl(TestDao testDao, PersonDao personDao, PersonAnswerDao personAnswerDao) {
        this.testDao = testDao;
        this.personDao = personDao;
        this.personAnswerDao = personAnswerDao;
    }

    @Override
    public PersonAnswer runTest() {
        Person person = personDao.getPerson();
        Test test = testDao.getTest();
        return personAnswerDao.getPersonAnswer(person, test);
    }
}
