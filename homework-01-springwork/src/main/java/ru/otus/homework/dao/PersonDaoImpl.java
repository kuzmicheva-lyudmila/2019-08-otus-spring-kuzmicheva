package ru.otus.homework.dao;

import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Person;

@Service
public class PersonDaoImpl implements PersonDao {
    @Override
    public Person getPerson(CommunicationDao communicationDao) {
        String surname = communicationDao.getAnswerMessage("enter.surname", "enter.error", "[^\\d]+");
        String name = communicationDao.getAnswerMessage("enter.name", "enter.error", "[^\\d]+");
        return new Person(surname, name);
    }
}
