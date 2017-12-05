package vs.mail.facade.sender;

import org.junit.Assert;
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
import vs.mail.facade.sender.client.AsyncEmailClient;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AsyncSenderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncSenderTest.class);

    @Test
    public void defaultSenderWithSslPlainText(){
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        Assert.assertTrue(response.isPresent());

        Assert.assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        Assert.assertEquals(response.get().getDescription(), "Email has been submitted to send Asynchronously");

        LOGGER.info("Asynchronous Email [{}] Sending with -SSL- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithSslHTMLTemplate(){
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        assertEquals(response.get().getDescription(), "Email has been submitted to send Asynchronously");

        LOGGER.info("Async Email [{}] Sending with -SSL- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithTlsPlainText(){
        Configuration conf = getConfigurationTls();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        Assert.assertTrue(response.isPresent());

        Assert.assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        Assert.assertEquals(response.get().getDescription(), "Email has been submitted to send Asynchronously");

        LOGGER.info("Async Email [{}] Sending with -TLS- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithTlsHTMLTemplate(){
        Configuration conf = getConfigurationTls();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUBMITTED_TO_SEND);
        assertEquals(response.get().getDescription(), "Email has been submitted to send Asynchronously");

        LOGGER.info("Async Email [{}] Sending with -TLS- Success.", email.getEmailContent().getContentType());
    }

    @Test(expected = SecurityException.class)
    public void defaultSenderWithoutSecPlainText(){
        Configuration conf = getConfigurationNoSec();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.FAILED);
        assertEquals(response.get().getDescription(), "Email Sending Failed without Security");

        LOGGER.error("Async Email [{}] Sending without security Failed.", email.getEmailContent().getContentType());
    }

    @Test(expected = SecurityException.class)
    public void defaultSenderWithoutSecHTMLTemplate(){
        Configuration conf = getConfigurationNoSec();

        assertNotEquals(conf, null);

        AsyncEmailClient client = new AsyncEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.FAILED);
        assertEquals(response.get().getDescription(), "Email Sending Failed without Security");

        LOGGER.error("Async Email [{}] Sending without security Failed.", email.getEmailContent().getContentType());
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

    private Email getEmailWithRecipients(EmailContent content){
        ArrayList<String> recipients = new ArrayList<>();
        recipients.add("ayesh9206@gmail.com");
        recipients.add("apeksha123sahana@gmail.com");
        Email email = new EmailBuilder()
                .setSender("tcaascli@gmail.com")
                .setRecipients(recipients)
                .setSubject("Test Subject")
                .setEmailContent(content)
                .createEmail();
        return email;
    }
}
