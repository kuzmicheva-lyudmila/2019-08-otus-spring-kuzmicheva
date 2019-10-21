package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonDao;
import ru.otus.homework.dao.PersonDaoImpl;
import ru.otus.homework.domain.Person;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AuthorizationServiceImplTest {

    @Configuration
    static class TestServiceImplContextConfiguration {
        @Bean
        public PersonDao personDao() {
            return new PersonDaoImpl();
        }

        @Bean
        public AuthorizationService authorizationService(PersonDao personDao) {
            return new AuthorizationServiceImpl(personDao);
        }
    }

    @MockBean
    private CommunicationDao communicationDao;

    @SpyBean
    private PersonDao personDao;

    @Autowired
    private AuthorizationService authorizationService;

    @Test
    void getPerson() {
        Person person = new Person("Surname", "Name");

        when(communicationDao.getAnswerMessage(any(), any(), any())).thenReturn("Surname").thenReturn("Name");

        Person authorizationPerson = authorizationService.getPerson(communicationDao);

        assertThat(authorizationPerson.toString()).isEqualTo(person.toString());
        verify(communicationDao, atLeastOnce()).getAnswerMessage(any(), any(), any());
        verify(personDao, times(1)).getPerson(communicationDao);
    }
}