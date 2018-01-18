package vs.mail.facade.sender.client.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.SecType;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.api.response.EmailResponseBuilder;
import vs.mail.facade.api.response.EmailStatus;
import vs.mail.facade.sender.executor.EmailExecutor;
import vs.mail.facade.sender.helper.EmailClientHelper;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public final class AsyncRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncRunner.class);

    protected AsyncRunner() {
    }

    public static Optional<EmailResponse> doExecute(Configuration configuration,
                                                    Email email,
                                                    EmailExecutor emailExecutor,
                                                    Executor executorService) {
        LOGGER.info("Sending Email [{}] with Configurations [{}]", email, configuration);
        if (configuration.getSecType().equals(SecType.NONE)) {
            return Optional.of(
                    EmailClientHelper.sendWithoutSecurity(email, configuration, emailExecutor));
        }
        if (configuration.getSecType().equals(SecType.SSL)) {
            CompletableFuture.runAsync(() -> {
                EmailResponse response = EmailClientHelper.sendWithSsl(email, configuration, emailExecutor);
                LOGGER.info("EmailSendResponse [{}]", response);
            }, executorService);
            return Optional.of(getGenericEmailResponseForAsyncSubmit());
        }
        if (configuration.getSecType().equals(SecType.TLS)) {
            CompletableFuture.runAsync(() -> {
                EmailResponse response = EmailClientHelper.sendWithTls(email, configuration, emailExecutor);
                LOGGER.info("EmailSendResponse [{}]", response);
            }, executorService);
            return Optional.of(getGenericEmailResponseForAsyncSubmit());
        }
        return Optional.empty();
    }

    public static EmailResponse getGenericEmailResponseForAsyncSubmit() {
        return new EmailResponseBuilder().setStatus(EmailStatus.SUBMITTED_TO_SEND)
                .setDescription("Email has been submitted to send Asynchronously")
                .createEmailResponse();
    }
}
