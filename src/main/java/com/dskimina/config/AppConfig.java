package com.dskimina.config;

import com.dskimina.domain.SimplePasswordAuthenticator;
import com.dskimina.services.MailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

@Configuration
public class AppConfig {


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
