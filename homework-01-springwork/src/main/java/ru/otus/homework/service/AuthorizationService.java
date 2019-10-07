package ru.otus.homework.service;

import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.domain.Person;

public interface AuthorizationService {
    Person getPerson(CommunicationDao communicationDao);
}
