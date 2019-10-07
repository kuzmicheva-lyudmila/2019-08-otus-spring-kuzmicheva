package ru.otus.homework;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.homework.service.TestService;

@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan
public class MainConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms
                = new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("CP1251");
        return ms;
    }
}
