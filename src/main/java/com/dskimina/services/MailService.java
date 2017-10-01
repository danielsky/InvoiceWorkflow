package com.dskimina.services;

import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class MailService extends Authenticator{

    private static final Log LOG = LogFactory.getLog(MailService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TemplateEngine templateEngine;

    private boolean senderEnabled = true;

    public String prepareServiceRequestCreatedMessage(ServiceRequest serviceRequest, User approver){
        Context context = new Context();
        context.setVariable("user", approver);
        context.setVariable("serviceRequest", serviceRequest);
        return templateEngine.process("mail/invoice-created", context);
    }

    @Async
    public void sendEmail(User approver, String content, String topic){
        if(senderEnabled) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                message.setFrom(new InternetAddress("dskimina.dev@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(approver.getEmail()));
                message.setSubject(topic);
                message.setContent(content, "text/html; charset=UTF-8");

                mailSender.send(message);
                Transport.send(message);
                LOG.info("Send email to: " + approver.getEmail() + " complete");
            } catch (MessagingException e) {
                LOG.warn("Cannot send email", e);
            }
        }
    }

    public void setSenderEnabled(boolean senderEnabled) {
        this.senderEnabled = senderEnabled;
    }
}
