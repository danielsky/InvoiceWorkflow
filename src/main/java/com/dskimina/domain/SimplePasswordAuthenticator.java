package com.dskimina.domain;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

public class SimplePasswordAuthenticator extends Authenticator {

    private Properties credentialsProperties;

    public SimplePasswordAuthenticator(Properties credentialsProperties) {
        this.credentialsProperties = credentialsProperties;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
                credentialsProperties.getProperty("username"),
                credentialsProperties.getProperty("password")
        );
    }
}
