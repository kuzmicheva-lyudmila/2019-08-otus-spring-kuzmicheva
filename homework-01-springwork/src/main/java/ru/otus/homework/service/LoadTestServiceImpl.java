package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.TestDao;
import ru.otus.homework.domain.PersonTest;

@Service
public class LoadTestServiceImpl implements LoadTestService{
    private final TestDao testDao;
    private final CommunicationDao communicationDao;

    public LoadTestServiceImpl(TestDao testDao, CommunicationDao communicationDao) {
        this.testDao = testDao;
        this.communicationDao = communicationDao;
    }

    @Override
    public PersonTest getTest() {
        return testDao.getTest(communicationDao);
    }
}
