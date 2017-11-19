package vs.mail.facade.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.ConfigurationBuilder;
import vs.mail.facade.api.config.SecType;

import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForSslSecurity;
import static vs.mail.facade.config.EmailPropertyBuilder.getPropertiesForTlsSecurity;
import static vs.mail.facade.property.DefaultPropertyKeys.*;
import static vs.mail.facade.property.DefaultPropertyValues.*;

public class EmailPropertyTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailPropertyTest.class);

    @Test
    public void EmailPropertyBuilderTest(){
        Configuration sslConf = new ConfigurationBuilder()
                .setUsername("test")
                .setPassword("password")
                .setSmtpDebugEnable(false)
                .setSecType(SecType.SSL)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
        Properties sslProperties = getPropertiesForSslSecurity(sslConf);
        assertNotEquals(sslConf, null);
        assertNotEquals(sslProperties, null);
        assertEquals(sslProperties.getProperty(MAIL_SMTP_DEBUG), Boolean.toString(sslConf.isSmtpDebugEnable()));
        assertEquals(sslProperties.getProperty(MAIL_SMTP_HOST), sslConf.getSmtpHost());
        assertEquals(sslProperties.getProperty(MAIL_SMTP_PORT), SSL_PORT);
        assertEquals(sslProperties.getProperty(MAIL_SMTP_AUTH), Boolean.toString(AUTH_ENABLE));
        assertEquals(sslProperties.getProperty(MAIL_SMTP_SOCK_CLASS), SMTP_SOCK_FACTORY);
        LOGGER.info("Email-SSL-Property Builder Test Successful");

        Configuration tlsConf = new ConfigurationBuilder()
                .setUsername("test")
                .setPassword("password")
                .setSmtpDebugEnable(false)
                .setSecType(SecType.TLS)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
        Properties tlsProperties = getPropertiesForTlsSecurity(tlsConf);
        assertNotEquals(tlsConf, null);
        assertNotEquals(tlsProperties, null);
        assertEquals(tlsProperties.getProperty(MAIL_SMTP_DEBUG), Boolean.toString(tlsConf.isSmtpDebugEnable()));
        assertEquals(tlsProperties.getProperty(MAIL_SMTP_HOST), tlsConf.getSmtpHost());
        assertEquals(tlsProperties.getProperty(MAIL_SMTP_PORT), TLS_PORT);
        assertEquals(tlsProperties.getProperty(MAIL_SMTP_AUTH), Boolean.toString(AUTH_ENABLE));
        assertEquals(tlsProperties.getProperty(MAIL_SMTP_START_TLS), Boolean.toString(START_TLS_ENABLE));
        LOGGER.info("Email-TLS-Property Builder Test Successful");
    }
}
