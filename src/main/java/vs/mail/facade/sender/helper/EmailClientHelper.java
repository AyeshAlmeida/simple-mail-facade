package vs.mail.facade.sender.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.exception.SecurityException;
import vs.mail.facade.sender.executor.EmailExecutor;

import javax.mail.Session;
import java.util.Properties;

import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForSslSecurity;
import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForTlsSecurity;
import static vs.mail.facade.util.EmailClientHelper.getAuthenticator;

public final class EmailClientHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailClientHelper.class);

    protected EmailClientHelper() {
    }

    public static EmailResponse sendWithoutSecurity(final Email email,
                                                    final Configuration configuration,
                                                    final EmailExecutor executor) {
        LOGGER.error("SecurityException Occurred while sending Email via non-secure channel");
        throw new SecurityException("Cannot Send Mails without Authentication");
    }

    public static EmailResponse sendWithSsl(final Email email,
                                            final Configuration configuration,
                                            final EmailExecutor executor) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForSslSecurity(configuration);
        Session session = Session.getDefaultInstance(properties, getAuthenticator(configuration));
        session.setDebug(configuration.isSmtpDebugEnable());
        return executor.fireEmail(email, session);
    }

    public static EmailResponse sendWithTls(final Email email,
                                            final Configuration configuration,
                                            final EmailExecutor executor) {
        LOGGER.info("Mail is sending over {}", configuration.getSecType());
        Properties properties = getPropertiesForTlsSecurity(configuration);
        Session session = Session.getInstance(properties, getAuthenticator(configuration));
        session.setDebug(configuration.isSmtpDebugEnable());
        return executor.fireEmail(email, session);
    }
}
