package vs.mail.facade.api.config;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConfigurationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationTest.class);

    @Test
    public void confBuilderTest(){
        Configuration conf = new ConfigurationBuilder()
                .setUsername("test")
                .setPassword("password")
                .setSmtpDebugEnable(false)
                .setSecType(SecType.NONE)
                .setSmtpHost("smtp.gmail.com")
                .createConfiguration();
        assertNotEquals(conf, null);
        assertEquals(conf.getUsername(), "test");
        assertEquals(conf.getPassword(), "password");
        assertEquals(conf.getSecType(), SecType.NONE);
        assertEquals(conf.getSmtpHost(), "smtp.gmail.com");
        LOGGER.info("ConfigurationBuilderTest successful");
    }

}
