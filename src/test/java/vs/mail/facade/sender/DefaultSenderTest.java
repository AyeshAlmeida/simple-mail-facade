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
import vs.mail.facade.exception.NoRecipientException;
import vs.mail.facade.exception.SecurityException;
import vs.mail.facade.sender.client.DefaultEmailClient;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DefaultSenderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSenderTest.class);

    @Test
    public void defaultSenderWithSslPlainText(){
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUCCESS);
        assertEquals(response.get().getDescription(), "Email Sent Successfully");

        LOGGER.info("Default Email [{}] Sending with -SSL- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithSslHTMLTemplate(){
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUCCESS);
        assertEquals(response.get().getDescription(), "Email Sent Successfully");

        LOGGER.info("Default Email [{}] Sending with -SSL- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithTlsPlainText(){
        Configuration conf = getConfigurationTls();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUCCESS);
        assertEquals(response.get().getDescription(), "Email Sent Successfully");

        LOGGER.info("Default Email [{}] Sending with -TLS- Success.", email.getEmailContent().getContentType());
    }

    @Test
    public void defaultSenderWithTlsHTMLTemplate(){
        Configuration conf = getConfigurationTls();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUCCESS);
        assertEquals(response.get().getDescription(), "Email Sent Successfully");

        LOGGER.info("Default Email [{}] Sending with -TLS- Success.", email.getEmailContent().getContentType());
    }

    @Test(expected = SecurityException.class)
    public void defaultSenderWithoutSecPlainText(){
        Configuration conf = getConfigurationNoSec();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.FAILED);
        assertEquals(response.get().getDescription(), "Email Sending Failed without Security");

        LOGGER.error("Default Email [{}] Sending without security Failed.", email.getEmailContent().getContentType());
    }

    @Test(expected = SecurityException.class)
    public void defaultSenderWithoutSecHTMLTemplate(){
        Configuration conf = getConfigurationNoSec();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithRecipients(new EmailContent(EmailContentType.HTML, "/vs/templates/email-Temp.html"));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.FAILED);
        assertEquals(response.get().getDescription(), "Email Sending Failed without Security");

        LOGGER.error("Default Email [{}] Sending without security Failed.", email.getEmailContent().getContentType());
    }

    @Test(expected = NoRecipientException.class)
    public void sendEmailWithoutRecipient(){
        Configuration conf = getConfigurationSsl();

        assertNotEquals(conf, null);

        DefaultEmailClient client = new DefaultEmailClient(conf);

        assertNotEquals(client, null);

        Email email = getEmailWithoutRecipients(new EmailContent(EmailContentType.TEXT, "This is a Test Content."));

        assertNotEquals(email, null);

        Optional<EmailResponse> response = client.sendEmail(email);

        assertTrue(response.isPresent());

        assertEquals(response.get().getStatus(), EmailStatus.SUCCESS);
        assertEquals(response.get().getDescription(), "Email Sent Successfully");

        LOGGER.error("Default Email [{}] Sending email without recipients failed.", email.getEmailContent().getContentType());
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
        ArrayList<String> carbonCoppied = new ArrayList<>();
        carbonCoppied.add("ccuserone@cuvox.de");
        carbonCoppied.add("ccusertwo@jourrapide.com");
        ArrayList<String> blindCarbonCoppied = new ArrayList<>();
        blindCarbonCoppied.add("bccone@jourrapide.com");
        blindCarbonCoppied.add("bcctwo@jourrapide.com");
        Email email = new EmailBuilder()
                .setSender("tcaascli@gmail.com")
                .setRecipients(recipients)
                .setCarbonCopied(carbonCoppied)
                .setBlindCarbonCopied(blindCarbonCoppied)
                .setSubject("Test Subject")
                .setEmailContent(content)
                .createEmail();
        return email;
    }

    private Email getEmailWithoutRecipients(EmailContent content){
        Email email = new EmailBuilder()
                .setSender("tcaascli@gmail.com")
                .setSubject("Test Subject")
                .setEmailContent(content)
                .createEmail();
        return email;
    }
}
