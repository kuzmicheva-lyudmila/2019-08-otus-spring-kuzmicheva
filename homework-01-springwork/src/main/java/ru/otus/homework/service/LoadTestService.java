package ru.otus.homework.service;

import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.domain.PersonTest;

public interface LoadTestService {
    PersonTest getTest(CommunicationDao communicationDao);
}
