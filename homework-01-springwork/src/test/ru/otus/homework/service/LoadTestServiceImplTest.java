package ru.otus.homework.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.TestDao;
import ru.otus.homework.dao.TestDaoImpl;
import ru.otus.homework.domain.PersonTest;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoadTestServiceImplTest {
    @Configuration
    static class TestServiceImplContextConfiguration {
        @Bean
        public LoadTestService loadTestService(TestDao testDao) {
            return new LoadTestServiceImpl(testDao);
        }
    }

    @MockBean
    MainConfig mainConfig;

    @MockBean
    TestDao testDao;

    @MockBean
    CommunicationDao communicationDao;

    @Autowired
    LoadTestService loadTestService;

    @Test
    void getTest() {
        PersonTest personTest = new PersonTest(new HashMap<>());

        when(testDao.getTest(communicationDao)).thenReturn(personTest);

        PersonTest loadedPersonTest = loadTestService.getTest(communicationDao);

        assertThat(loadedPersonTest).isEqualTo(personTest);
    }
}