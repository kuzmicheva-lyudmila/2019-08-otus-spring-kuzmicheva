package ru.otus.homework.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.homework.dao.CommunicationDao;
import ru.otus.homework.domain.Person;
import ru.otus.homework.domain.PersonAnswer;
import ru.otus.homework.domain.PersonTest;
import ru.otus.homework.service.AuthorizationService;
import ru.otus.homework.service.LoadTestService;
import ru.otus.homework.service.TestService;

@ShellComponent
public class ShellMainCommand {
    private final AuthorizationService authorizationService;
    private final LoadTestService loadTestService;
    private final TestService testService;
    private final CommunicationDao communicationDao;

    private Person person;
    private PersonTest personTest;
    private PersonAnswer personAnswer;

    public ShellMainCommand(AuthorizationService authorizationService, LoadTestService loadTestService, TestService testService, CommunicationDao communicationDao) {
        this.authorizationService = authorizationService;
        this.loadTestService = loadTestService;
        this.testService = testService;
        this.communicationDao = communicationDao;
    }

    @ShellMethod(value = "Authorization", key = {"login", "l"})
    public String authorization() {
        person = authorizationService.getPerson(communicationDao);
        personTest = null;
        personAnswer = null;

        return "Login: " + person.toString();
    }

    @ShellMethod(value = "LoadTest", key = {"load-test", "lt"})
    @ShellMethodAvailability(value = "isLoadTestAvailable")
    public String loadTest() {
        personTest = loadTestService.getTest(communicationDao);
        return "Test loaded: " + personTest.toString();
    }

    @ShellMethod(value = "runTest", key = {"run-test", "rt"})
    @ShellMethodAvailability(value = "isRunTestAvailable")
    public String runTest() {
        personAnswer = testService.runTest(person, personTest, communicationDao);
        testService.getResultTest(personAnswer, communicationDao);
        return "Test runned successfully";
    }

    private Availability isLoadTestAvailable() {
        return person == null? Availability.unavailable("Need authorization!"): Availability.available();
    }

    private Availability isRunTestAvailable() {
        return personTest == null? Availability.unavailable("Load test!"): Availability.available();
    }
}
