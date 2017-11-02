package com.dskimina.services;

import com.dskimina.containers.MailHolder;
import com.dskimina.data.ResetCode;
import com.dskimina.data.SecurityCode;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
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
import java.util.Locale;

@Service
public class MailService extends Authenticator{

    private static final Log LOG = LogFactory.getLog(MailService.class);

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private TemplateEngine templateEngine;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private Environment env;

    private boolean senderEnabled = true;

    public MailHolder prepareServiceRequestCreatedMessage(ServiceRequest serviceRequest, User approver, SecurityCode securityCode){
        MailHolder mailHolder = new MailHolder();

        Context context = new Context(approver.getLocale());
        context.setVariable("user", approver);
        context.setVariable("serviceRequest", serviceRequest);
        context.setVariable("securityCode", securityCode);
        context.setVariable("host", env.getProperty("workflow.context"));

        mailHolder.setContent(templateEngine.process("mail/invoice-created", context));
        mailHolder.setSubject(messageSource.getMessage("mail.subject.serviceRequest.created", null, approver.getLocale()));
        mailHolder.setRecipient(approver.getEmail());
        return mailHolder;
    }

    public MailHolder prepareResetPasswordMessage(User user, ResetCode resetCode){
        MailHolder mailHolder = new MailHolder();
        Locale locale = user.getLocale();

        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("resetCode", resetCode);
        context.setVariable("host", env.getProperty("workflow.context"));

        mailHolder.setContent(templateEngine.process("mail/reset-password", context));
        mailHolder.setSubject(messageSource.getMessage("mail.subject.resetPassword", null, locale));
        mailHolder.setRecipient(user.getEmail());
        return mailHolder;
    }

    @Async
    public void sendEmail(MailHolder mailHolder){
        if(senderEnabled) {
            try {
                MimeMessage message = mailSender.createMimeMessage();
                message.setFrom(new InternetAddress("dskimina.dev@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailHolder.getRecipient()));
                message.setSubject(mailHolder.getSubject());
                message.setContent(mailHolder.getContent(), "text/html; charset=UTF-8");

                mailSender.send(message);
                Transport.send(message);
                LOG.info("Send email to: " + mailHolder.getRecipient() + " complete");
            } catch (MessagingException e) {
                LOG.warn("Cannot send email", e);
            }
        }
    }

    public void setSenderEnabled(boolean senderEnabled) {
        this.senderEnabled = senderEnabled;
    }
}
