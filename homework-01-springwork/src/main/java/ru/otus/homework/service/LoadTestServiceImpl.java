package ru.otus.homework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.TestDao;
import ru.otus.homework.domain.PersonTest;

@Service
public class LoadTestServiceImpl implements LoadTestService{
    private final TestDao testDao;

    public LoadTestServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public PersonTest getTest(CommunicationDao communicationDao) {
        return testDao.getTest(communicationDao);
    }
}
