package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonAnswerDao;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;

@Service
public class TestServiceImpl implements TestService {
    private final Person person;
    private final PersonTest test;
    private final PersonAnswerDao personAnswerDao;
    private final CommunicationDao communicationDao;

    public TestServiceImpl(CommunicationDao communicationDao, PersonAnswerDao personAnswerDao, AuthorizationService authorizationService, LoadTestService loadTestService) {
        this.communicationDao = communicationDao;
        this.person = authorizationService.getPerson(this.communicationDao);
        this.test = loadTestService.getTest(this.communicationDao);
        this.personAnswerDao = personAnswerDao;
    }

    @Override
    public void runTest() {
        PersonAnswer personAnswer = personAnswerDao.getPersonAnswer(person, test, communicationDao);
        personAnswerDao.getResultTest(personAnswer, communicationDao);
    }
}
