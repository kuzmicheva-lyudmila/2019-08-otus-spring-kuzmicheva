package ru.otus.homework.dao;

import ru.otus.homework.domain.PersonTest;

public interface TestDao {
    PersonTest getTest(CommunicationDao communicationDao);
}
