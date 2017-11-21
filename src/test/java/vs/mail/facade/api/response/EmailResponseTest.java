package vs.mail.facade.api.response;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class EmailResponseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailResponseTest.class);

    @Test
    public void EmailResponseBuilderTest(){
        EmailResponse response = new EmailResponseBuilder()
                .setStatus(EmailStatus.FAILED)
                .setDescription("Email Sending Failed")
                .createEmailResponse();
        assertNotEquals(response, null);
        assertEquals(response.getStatus(), EmailStatus.FAILED);
        assertEquals(response.getDescription(), "Email Sending Failed");
        LOGGER.info("EmailResponse Builder Test Successful");
    }

}
