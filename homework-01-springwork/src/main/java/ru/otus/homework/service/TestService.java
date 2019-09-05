package ru.otus.homework.service;

import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.Test;

public interface TestService {
    PersonAnswer runTest();
}
