package vs.mail.facade.sender.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.sender.executor.DefaultExecutor;
import vs.mail.facade.sender.executor.EmailExecutor;

public final class AsyncEmailClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncEmailClient.class);
    private static final EmailExecutor executor = DefaultExecutor.getDefaultExecutor();
    private final Configuration configuration;

    public AsyncEmailClient(Configuration configuration) {
        this.configuration = configuration;
    }


}
