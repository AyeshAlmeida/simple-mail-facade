package vs.mail.facade.config;

import vs.mail.facade.api.config.Configuration;

import java.util.Properties;

import static vs.mail.facade.property.DefaultPropertyKeys.*;
import static vs.mail.facade.property.DefaultPropertyValues.*;

public final class EmailPropertyBuilder {
    public static Properties getPropertiesForSslSecurity(final Configuration configuration) {
        Properties properties = System.getProperties();
        properties.put(MAIL_SMTP_DEBUG, Boolean.toString(configuration.isSmtpDebugEnable()));
        properties.setProperty(MAIL_SMTP_HOST, configuration.getSmtpHost());
        properties.put(MAIL_SMTP_PORT, SSL_PORT);
        properties.put(MAIL_SMTP_AUTH, Boolean.toString(AUTH_ENABLE));
        properties.put(MAIL_SMTP_SOCK_CLASS, SMTP_SOCK_FACTORY);
        return properties;
    }

    public static Properties getPropertiesForTlsSecurity(final Configuration configuration) {
        Properties properties = new Properties();
        properties.put(MAIL_SMTP_DEBUG, Boolean.toString(configuration.isSmtpDebugEnable()));
        properties.setProperty(MAIL_SMTP_HOST, configuration.getSmtpHost());
        properties.put(MAIL_SMTP_PORT, TLS_PORT);
        properties.put(MAIL_SMTP_AUTH, Boolean.toString(AUTH_ENABLE));
        properties.put(MAIL_SMTP_START_TLS, Boolean.toString(START_TLS_ENABLE));
        return properties;
    }
}
