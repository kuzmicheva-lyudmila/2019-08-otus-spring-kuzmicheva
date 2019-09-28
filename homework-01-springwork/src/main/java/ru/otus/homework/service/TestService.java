package ru.otus.homework.service;

import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;

public interface TestService {
    PersonAnswer runTest(Person person, PersonTest test);
    void getResultTest(PersonAnswer personAnswer);
}
