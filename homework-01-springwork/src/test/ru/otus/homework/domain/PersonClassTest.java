package ru.otus.homework.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class Person")
class PersonClassTest {

    @Test
    @DisplayName("correct")
    void getFIO() {
        Person person = new Person("Surname", "name");
        assertEquals(person.getFIO(), "Surname name");
    }
}