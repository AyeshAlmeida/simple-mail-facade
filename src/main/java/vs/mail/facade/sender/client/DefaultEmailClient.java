package vs.mail.facade.sender.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.SecType;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.exception.SecurityException;
import vs.mail.facade.sender.executor.DefaultExecutor;

import javax.mail.Session;
import java.util.Optional;
import java.util.Properties;

import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForSslSecurity;
import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForTlsSecurity;
import static vs.mail.facade.util.EmailClientHelper.getAuthenticator;

public final class DefaultEmailClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmailClient.class);
    private static final DefaultExecutor executor = DefaultExecutor.getDefaultExecutor();
    private final Configuration configuration;

    public DefaultEmailClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public Optional<EmailResponse> sendEmail(final Email email) {
        LOGGER.info("Sending Email [{}] with Configurations [{}]", email, configuration);
        if (configuration.getSecType().equals(SecType.NONE)) {
            return Optional.of(sendWithoutSecurity(email));
        }
        if (configuration.getSecType().equals(SecType.SSL)) {
            return Optional.of(sendWithSsl(email));
        }
        if (configuration.getSecType().equals(SecType.TLS)) {
            return Optional.of(sendWithTls(email));
        }
        return Optional.empty();
    }

    private EmailResponse sendWithoutSecurity(final Email email) {
        LOGGER.error("SecurityException Occurred while sending Email via non-secure channel");
        throw new SecurityException("Cannot Send Mails without Authentication");
    }

    private EmailResponse sendWithSsl(final Email email) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForSslSecurity(configuration);
        Session session = Session.getDefaultInstance(properties, getAuthenticator(configuration));
        session.setDebug(configuration.isSmtpDebugEnable());
        return executor.fireEmail(email, session);
    }

    private EmailResponse sendWithTls(final Email email) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForTlsSecurity(configuration);
        Session session = Session.getInstance(properties, getAuthenticator(configuration));
        session.setDebug(configuration.isSmtpDebugEnable());
        return executor.fireEmail(email, session);
    }
}
