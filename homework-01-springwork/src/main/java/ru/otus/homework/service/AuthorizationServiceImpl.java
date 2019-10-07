package ru.otus.homework.service;

import org.springframework.stereotype.Service;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonDao;
import ru.otus.homework.domain.Person;

@Service
public class AuthorizationServiceImpl implements AuthorizationService{
    private final PersonDao personDao;

    public AuthorizationServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public Person getPerson(CommunicationDao communicationDao) {
        return personDao.getPerson(communicationDao);
    }
}
