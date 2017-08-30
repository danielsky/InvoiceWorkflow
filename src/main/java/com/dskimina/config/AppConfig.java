package com.dskimina.config;

import com.dskimina.domain.SimplePasswordAuthenticator;
import com.dskimina.services.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.mail.Session;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {


    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Executor-");
        executor.initialize();
        return executor;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }


    @Bean
    @Profile("dev")
    public JavaMailSender getMailSession() throws IOException{

        Properties smtpProperties = new Properties();
        smtpProperties.load(MailService.class.getResourceAsStream("/mail.properties"));

        Properties credentialsProperties = new Properties();
        credentialsProperties.load(MailService.class.getResourceAsStream("/mail-credentials.properties"));

        Session session = Session.getDefaultInstance(smtpProperties, new SimplePasswordAuthenticator(credentialsProperties));
        JavaMailSenderImpl senderImpl  = new JavaMailSenderImpl();
        senderImpl.setSession(session);

        return senderImpl;
    }
}
