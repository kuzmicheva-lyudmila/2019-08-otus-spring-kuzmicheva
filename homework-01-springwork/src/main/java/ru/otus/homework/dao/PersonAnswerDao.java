package ru.otus.homework.dao;

import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.Test;

public interface PersonAnswerDao {
    PersonAnswer getPersonAnswer(Person person, Test test);
}
