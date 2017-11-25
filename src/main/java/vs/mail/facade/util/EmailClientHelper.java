package vs.mail.facade.util;

import vs.mail.facade.api.config.Configuration;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public final class EmailClientHelper {
    public static Authenticator getAuthenticator(final Configuration configuration) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configuration.getUsername(), configuration.getPassword());
            }
        };
    }
}
