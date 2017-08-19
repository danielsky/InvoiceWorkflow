package com.dskimina.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService extends Authenticator{

    private static final Log LOG = LogFactory.getLog(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    /*private final Properties smtpProperties;
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
    }*/

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

    @Async
    public void sendEmail(String email, String content, String topic){

        try {

            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress("dskimina.dev@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject(topic);
            //message.setText(content);
            message.setContent(content, "text/html; charset=UTF-8");

            mailSender.send(message);
            //Transport.send(message);
            LOG.info("Send email to: "+email+" complete");
        } catch (MessagingException e) {
            LOG.warn("Cannot send email", e);
        }
    }
}
