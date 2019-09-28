package ru.otus.homework.dao;

import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;

public interface PersonAnswerDao {
    PersonAnswer getPersonAnswer(Person person, PersonTest test, CommunicationDao communicationDao);
    void getResultTest(PersonAnswer personAnswer, CommunicationDao communicationDao);
}
