package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.dao.PersonAnswerDao;
import ru.otus.homework.service.AuthorizationService;
import ru.otus.homework.service.LoadTestService;
import ru.otus.homework.service.TestService;
import ru.otus.homework.service.TestServiceImpl;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestServiceImplTest {
    @MockBean
    private AuthorizationService authorizationService;

    @MockBean
    private LoadTestService loadTestService;

    @MockBean
    private CommunicationDao communicationDao;

    @MockBean
    private PersonAnswerDao personAnswerDao;

    @Autowired
    private TestService testService;

    @Test
    public void runTest(){
        testService.runTest();

        Mockito.verify(authorizationService, Mockito.times(1)).getPerson(communicationDao);
        Mockito.verify(loadTestService, Mockito.times(1)).getTest(communicationDao);
    }
}
