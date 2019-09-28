package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonDao;
import ru.otus.homework.domain.Person;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
    private final PersonDao personDao;
    private final CommunicationDao communicationDao;

    public AuthorizationServiceImpl(PersonDao personDao, CommunicationDao communicationDao) {
        this.personDao = personDao;
        this.communicationDao = communicationDao;
    }

    @Override
    public Person getPerson() {
        return personDao.getPerson(communicationDao);
    }
}
