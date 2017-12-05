package vs.mail.facade.sender.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.SecType;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.sender.executor.DefaultExecutor;
import vs.mail.facade.sender.executor.EmailExecutor;
import vs.mail.facade.sender.helper.EmailClientHelper;

import java.util.Optional;

public final class DefaultEmailClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultEmailClient.class);
    private static final EmailExecutor emailExecutor = DefaultExecutor.getDefaultExecutor();
    private final Configuration configuration;

    public DefaultEmailClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public Optional<EmailResponse> sendEmail(final Email email) {
        LOGGER.info("Sending Email [{}] with Configurations [{}]", email, configuration);
        if (configuration.getSecType().equals(SecType.NONE)) {
            return Optional.of(
                    EmailClientHelper.sendWithoutSecurity(email, configuration, emailExecutor));
        }
        if (configuration.getSecType().equals(SecType.SSL)) {
            return Optional.of(
                    EmailClientHelper.sendWithSsl(email, configuration, emailExecutor));
        }
        if (configuration.getSecType().equals(SecType.TLS)) {
            return Optional.of(
                    EmailClientHelper.sendWithTls(email, configuration, emailExecutor));
        }
        return Optional.empty();
    }
}
