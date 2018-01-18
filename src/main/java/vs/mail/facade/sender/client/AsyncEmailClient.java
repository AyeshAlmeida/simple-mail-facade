package vs.mail.facade.sender.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.sender.executor.DefaultExecutor;
import vs.mail.facade.sender.executor.EmailExecutor;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static vs.mail.facade.sender.client.async.AsyncRunner.doExecute;

public final class AsyncEmailClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncEmailClient.class);
    private static final EmailExecutor emailExecutor = DefaultExecutor.getDefaultExecutor();
    private final Executor executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() / 2);
    private final Configuration configuration;

    public AsyncEmailClient(Configuration configuration) {
        this.configuration = configuration;
    }

    public Optional<EmailResponse> sendEmail(final Email email) {
        return doExecute(configuration, email, emailExecutor, executorService);
    }
}
