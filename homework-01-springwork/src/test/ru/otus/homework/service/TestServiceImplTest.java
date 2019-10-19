package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonAnswerDao;
import ru.otus.homework.dao.PersonAnswerDaoImpl;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;
import ru.otus.homework.service.*;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class TestServiceImplTest {

    @Configuration
    static class TestServiceImplContextConfiguration {
        @Bean
        public MainConfig mainConfig() {
            return new MainConfig();
        }

        @Bean
        public PersonAnswerDao personAnswerDao(MainConfig mainConfig) {
            return new PersonAnswerDaoImpl(mainConfig);
        }

        @Bean
        public TestService testService(PersonAnswerDao personAnswerDao) {
            return new TestServiceImpl(personAnswerDao);
        }
    }

    @MockBean
    private CommunicationDao communicationDao;

    @Autowired
    private PersonAnswerDao personAnswerDao;

    @Autowired
    private TestService testService;

    @Test
    public void runTest(){
        Person person = new Person("Surname", "Name");
        PersonTest test = new PersonTest(new HashMap<>());

        PersonAnswer personAnswer = testService.runTest(person, test, communicationDao);
        assertThat(personAnswer.getPerson()).isEqualTo(person);
        assertThat(personAnswer.getPersonTest()).isEqualTo(test);

        testService.getResultTest(personAnswer, communicationDao);
        Mockito.verify(communicationDao, Mockito.atLeastOnce()).showMessageWithLocale(any(), any());
    }
}
