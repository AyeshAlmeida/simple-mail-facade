package vs.mail.facade.sender.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.SecType;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.api.response.EmailStatus;
import vs.mail.facade.exception.SecurityException;
import vs.mail.facade.sender.executor.DefaultExecutor;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForSslSecurity;
import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForTlsSecurity;

public final class DefaultEmailClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmailClient.class);
    private static final DefaultExecutor executor = DefaultExecutor.getDefaultExecutor();
    private final Configuration configuration;

    public DefaultEmailClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public EmailResponse sendEmail(final Email email) {
        LOGGER.info("Sending Email [{}] with Configurations [{}]", email, configuration);
        final EmailResponse response = new EmailResponse();
        if (configuration.getSecType().equals(SecType.NONE)) {
            sendWithoutSecurity(email, response);
        }
        if (configuration.getSecType().equals(SecType.SSL)) {
            sendWithSsl(email, response);
        }
        if (configuration.getSecType().equals(SecType.TLS)) {
            sendWithTls(email, response);
        }
        return response;
    }

    private void sendWithoutSecurity(final Email email, final EmailResponse response) {
        LOGGER.error("SecurityException Occurred while sending Email via non-secure channel");
        response.setStatus(EmailStatus.FAILED);
        response.setDescription("Email Sending Failed without Security");
        throw new SecurityException("Cannot Send Mails without Authentication");
    }

    private void sendWithSsl(final Email email, final EmailResponse response) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForSslSecurity(configuration);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configuration.getUsername(), configuration.getPassword());
            }
        });
        session.setDebug(configuration.isSmtpDebugEnable());
        executor.fireEmail(email, session, response);
    }

    private void sendWithTls(final Email email, final EmailResponse response) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForTlsSecurity(configuration);
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configuration.getUsername(), configuration.getPassword());
            }
        });
        session.setDebug(configuration.isSmtpDebugEnable());
        executor.fireEmail(email, session, response);
    }
}
