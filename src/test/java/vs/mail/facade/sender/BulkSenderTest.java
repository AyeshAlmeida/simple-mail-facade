package vs.mail.facade.sender;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.config.Configuration;
import vs.mail.facade.api.config.ConfigurationBuilder;
import vs.mail.facade.api.config.SecType;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.api.email.EmailBuilder;
import vs.mail.facade.api.email.content.EmailContent;
import vs.mail.facade.api.email.content.EmailContentType;
import vs.mail.facade.api.response.EmailResponse;
import vs.mail.facade.api.response.EmailStatus;
import vs.mail.facade.exception.SecurityException;
import vs.mail.facade.sender.client.BulkEmailClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


public class BulkSenderTest {
    private final Logger LOGGER = LoggerFactory.getLogger(BulkSenderTest.class);

    @Test
    public void bulkSenderWithSsl() {
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        BulkEmailClient client = new BulkEmailClient(conf);

        assertNotEquals(client, null);

        List<Email> emails = getListOfEmails();

        assertNotEquals(emails, null);

        Optional<EmailResponse> response = client.sendEmail(emails);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        assertEquals(response.get().getDescription(), "Emails have been submitted for bulk-send");

        LOGGER.info("{} Emails were Successfully submitted to send with -SSL- .", emails.size());
    }

    @Test
    public void bulkSenderWithTls() {
        Configuration conf = getConfigurationTls();

        assertNotEquals(conf, null);

        BulkEmailClient client = new BulkEmailClient(conf);

        assertNotEquals(client, null);

        List<Email> emails = getListOfEmails();

        assertNotEquals(emails, null);

        Optional<EmailResponse> response = client.sendEmail(emails);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        assertEquals(response.get().getDescription(), "Emails have been submitted for bulk-send");

        LOGGER.info("{} Emails were Successfully submitted to send with -TLS- .", emails.size());
    }

    @Test(expected = SecurityException.class)
    public void bulkSenderWithoutSecurity() {
        Configuration conf = getConfigurationNoSec();

        assertNotEquals(conf, null);

        BulkEmailClient client = new BulkEmailClient(conf);

        assertNotEquals(client, null);

        List<Email> emails = getListOfEmails();

        assertNotEquals(emails, null);

        Optional<EmailResponse> response = client.sendEmail(emails);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.FAILED);
        assertEquals(response.get().getDescription(), "Email Sending Failed without Security");

        LOGGER.info("{} Emails were Successfully submitted to send with -TLS- .", emails.size());
    }

    private List<Email> getListOfEmails() {
        List<Email> emails = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            emails.add(getEmailWithoutRecipients(
                    new EmailContent(EmailContentType.TEXT, "This is a Test Content.")));
        }
        return emails;
    }

    private Email getEmailWithoutRecipients(EmailContent content){
        ArrayList<String> recipients = new ArrayList<>();
        recipients.add("apeksha123sahana@gmail.com");
        Email email = new EmailBuilder()
                .setSender("tcaascli@gmail.com")
                .setRecipients(recipients)
                .setSubject("Test Subject")
                .setEmailContent(content)
                .createEmail();
        return email;
    }

    private Configuration getConfigurationSsl() {
        return new ConfigurationBuilder()
                .setUsername("tcaascli@gmail.com")
                .setPassword("Test123#")
                .setSmtpDebugEnable(true)
                .setSecType(SecType.SSL)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
    }

    private Configuration getConfigurationTls() {
        return new ConfigurationBuilder()
                .setUsername("tcaascli@gmail.com")
                .setPassword("Test123#")
                .setSmtpDebugEnable(true)
                .setSecType(SecType.TLS)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
    }

    private Configuration getConfigurationNoSec() {
        return new ConfigurationBuilder()
                .setUsername("tcaascli@gmail.com")
                .setPassword("Test123#")
                .setSmtpDebugEnable(true)
                .setSecType(SecType.NONE)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
    }
}
