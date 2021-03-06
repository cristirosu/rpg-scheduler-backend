package ro.fmi.rpg.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ro.fmi.rpg.dao.entity.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by User on 11/13/2016.
 */
@Service
public class EmailService {

    private static final String FROM = "dumpaul33@gmail.com";

    private Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Value("${dev}")
    private Boolean devMode;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(EmailType emailType, User user) {
        LOG.info("Trying to send email of type " + emailType + " to " + user.getEmail());
        String emailContent = getEmailContent(emailType, user);
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(user.getEmail());
            helper.setFrom(FROM);
            helper.setSubject(emailType.getTitle());
            helper.setText(emailContent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
        LOG.info("Sent email to " + user.getEmail() + " for content \"" + emailContent + "\"");
    }

    public void sendEmailWithContent(EmailType emailType, User user, String content) {
        LOG.info("Trying to send email of type " + emailType + " to " + user.getEmail());
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(user.getEmail());
            helper.setFrom(FROM);
            helper.setSubject(emailType.getTitle());
            helper.setText(content);
            helper.setText(content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
        LOG.info("Sent email to " + user.getEmail() + " for content \"" + content + "\"");
    }

    private String getEmailContent(EmailType emailType, User user){
        switch (emailType) {
            case ACTIVATION_EMAIL: {
                if(devMode){
                    return "http://localhost:3000/#/activation/" + user.getId();
                } else {
                    return "http://cristi.red:8080/#/activation/" + user.getId();
                }
            }
            default: {
                return "Empty email";
            }
        }
    }
}
