package vs.mail.facade.sender.executor;

import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.response.EmailResponse;

import javax.mail.Session;

public interface EmailExecutor {
    public EmailResponse fireEmail(final Email email, final Session session);
}
