package vs.mail.facade.sender.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.api.response.EmailResponseBuilder;
import vs.mail.facade.api.response.EmailStatus;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static vs.mail.facade.util.MimeMessageHelper.getMessage;

public final class DefaultExecutor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExecutor.class);
    private static final DefaultExecutor executor = new DefaultExecutor();

    private DefaultExecutor() {
    }

    public static DefaultExecutor getDefaultExecutor() {
        return executor;
    }

    public EmailResponse fireEmail(final Email email, final Session session) {
        EmailResponse response;
        try {
            MimeMessage message = getMessage(email, session);
            Transport.send(message);
            response = new EmailResponseBuilder()
                    .setStatus(EmailStatus.SUCCESS)
                    .setDescription("Email Sent Successfully")
                    .createEmailResponse();
            LOGGER.info("Email Send Successfully");
        } catch (MessagingException | IOException e) {
            response = new EmailResponseBuilder()
                    .setStatus(EmailStatus.FAILED)
                    .setDescription(e.getMessage())
                    .createEmailResponse();
            LOGGER.error("Exception occurred in sending Email {}", e);
        }
        return response;
    }
}
