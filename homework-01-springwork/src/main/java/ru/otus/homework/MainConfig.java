package ru.otus.homework;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("main-config")
public class MainConfig {
    private String fileName;
    private int countQuestions;
    private int countAnswerVariants;
    private int percentSuccessfulAnswer;
    private String localeLanguage;

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    public void setCountAnswerVariants(int countAnswerVariants) {
        this.countAnswerVariants = countAnswerVariants;
    }

    public void setPercentSuccessfulAnswer(int percentSuccessfulAnswer) {
        this.percentSuccessfulAnswer = percentSuccessfulAnswer;
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public String getFileName() {
        return fileName;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public int getCountAnswerVariants() {
        return countAnswerVariants;
    }

    public int getPercentSuccessfulAnswer() {
        return percentSuccessfulAnswer;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("CP1251");
        return ms;
    }
}
