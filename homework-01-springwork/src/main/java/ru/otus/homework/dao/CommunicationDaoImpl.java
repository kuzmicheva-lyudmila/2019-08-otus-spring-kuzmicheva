package ru.otus.homework.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;

import java.io.*;
import java.util.*;

@Service
public class CommunicationDaoImpl implements CommunicationDao {
    private static Logger logger = LoggerFactory.getLogger(CommunicationDaoImpl.class);
    private final Locale locale;
    private MessageSource messageSource;

    public CommunicationDaoImpl(@Value("${locale.language}") String localeLanguage) {
        this.locale = Locale.forLanguageTag(localeLanguage);
        this.messageSource = messageSource();
    }

    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("CP1251");
        return ms;
    }

    @Override
    public String getAnswerMessage(String questionString, String errorString, String template) {
        return getUserInputString(getMessageWithLocale(questionString, new String[]{}), getMessageWithLocale(errorString, new String[]{}), template);
    }

    @Override
    public void showMessage(String messageString) {
        System.out.println(messageString);
    }

    @Override
    public void showMessageWithLocale(String messageString, String[] stringParameters) {
        System.out.println(getMessageWithLocale(messageString, stringParameters));
    }

    private String getMessageWithLocale(String messageString, String[] stringParameters) {
        return messageSource.getMessage(messageString, stringParameters, locale);
    }

    private String getUserInputString(String message, String errorMessage, String template) {
        Scanner sc = new Scanner(System.in);
        String resultString = null;
        Boolean isFirstInput = true;
        do {
            if (isFirstInput) {
                System.out.println(message + ": ");
                isFirstInput = false;
            } else {
                System.out.println(errorMessage + ": ");
            }
            resultString = sc.nextLine();
        } while (!resultString.matches(template));
        return resultString.trim().toUpperCase();
    }

    public Map<String, Question> readTestFromCSVFile(int maxCountRows, int countAnswerVariants, String filename) {
        Map<String, Question> arrayQuestions = new HashMap<>();

        try (Reader reader = new InputStreamReader(ClassLoader.getSystemResourceAsStream(filename + '_' + locale.toLanguageTag() + ".csv"), "CP1251")) {
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().withDelimiter(';').parse(reader);
            int count = 0;
            for (CSVRecord record : records) {
                System.out.println(record.toString());
                String id = record.get("ID");
                String textQuestion = record.get("QUESTION");
                String rightAnswer = record.get("ANSWER");
                List<String> answerVariants = new ArrayList<>();
                for (int i = 1; i < Math.min(countAnswerVariants, record.size() - 3); i++) {
                    if (!record.get("VARIANT" + i).isEmpty()) {
                        answerVariants.add(record.get("VARIANT" + i));
                    }
                }
                arrayQuestions.put(id, new Question(id, textQuestion, rightAnswer, answerVariants));
                count++;
                if (count > maxCountRows) {
                    break;
                }
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        return arrayQuestions;
    }
}
