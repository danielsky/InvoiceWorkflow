package com.dskimina.services;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Service
public class MailService extends Authenticator{

    private static final Log LOG = LogFactory.getLog(MailService.class);

    private final Properties smtpProperties;
    private final Properties credentialsProperties;

    public MailService() throws IOException {
        smtpProperties = new Properties();
        smtpProperties.load(MailService.class.getResourceAsStream("/mail.properties"));

        credentialsProperties = new Properties();
        credentialsProperties.load(MailService.class.getResourceAsStream("/mail-credentials.properties"));
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
                credentialsProperties.getProperty("username"),
                credentialsProperties.getProperty("password")
        );
    }

    public String prepareMessage(String name){
        /*try {
            String template = IOUtils.toString(MailService.class.getResourceAsStream("/mail-templates/invoice-created.html"), "UTF8");
            return template.replaceAll("@NAME@", name);
        } catch (IOException e) {
            LOG.warn("Cannot prepare message", e);
            return null;
        }*/
        return "Hello "+name;
    }

    public void sendEmail(String email, String content, String topic){

        Session session = Session.getDefaultInstance(smtpProperties, this);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(credentialsProperties.getProperty("username")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(topic);
            //message.setText(content);
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

            LOG.info("Send email to: "+email+" complete");

        } catch (MessagingException e) {
            LOG.warn("Cannot send email", e);
        }
    }
}
