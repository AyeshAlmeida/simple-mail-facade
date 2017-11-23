package vs.mail.facade.api.email;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.email.content.EmailContent;
import vs.mail.facade.api.email.content.EmailContentType;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EmailTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTest.class);

    @Test
    public void EmailBuilderTest(){
        ArrayList<String> recipients = new ArrayList<>();
        recipients.add("rec1@gmail.com");
        recipients.add("rec2@gmail.com");
        ArrayList<String> carbonCoppied = new ArrayList<>();
        carbonCoppied.add("cc1@gmail.com");
        carbonCoppied.add("cc2@gmail.com");
        ArrayList<String> blindCarbonCoppied = new ArrayList<>();
        blindCarbonCoppied.add("bcc1@gmail.com");
        blindCarbonCoppied.add("bcc2@gmail.com");
        EmailContent emailContent = new EmailContent(EmailContentType.TEXT, "This is a Test Description.");
        Email email = new EmailBuilder()
                .setSender("test@test.lk")
                .setRecipients(recipients)
                .setCarbonCopied(carbonCoppied)
                .setBlindCarbonCopied(blindCarbonCoppied)
                .setSubject("Test Subject")
                .setEmailContent(emailContent)
                .createEmail();

        assertNotEquals(email, null);
        assertEquals(email.getSender(), "test@test.lk");
        assertEquals(email.getRecipients(), recipients);
        assertEquals(email.getCarbonCopied(), carbonCoppied);
        assertEquals(email.getBlindCarbonCopied(), blindCarbonCoppied);
        assertEquals(email.getSubject(), "Test Subject");
        assertEquals(email.getEmailContent(), emailContent);
        LOGGER.info("EmailBuilderTest is Successful");
    }

}
