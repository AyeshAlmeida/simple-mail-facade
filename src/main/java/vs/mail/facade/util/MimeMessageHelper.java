package vs.mail.facade.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vs.mail.facade.api.email.Email;
import vs.mail.facade.exception.NoRecipientException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static vs.mail.facade.property.DefaultPropertyValues.CONTENT_TYPE_TEXT_HTML;

public final class MimeMessageHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(MimeMessageHelper.class);

    public static MimeMessage getMessage(Email email, Session session) throws MessagingException, IOException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getSender()));
        addRecipients(email, message);
        message.setSubject(email.getSubject());
        setContent(email, message);
        return message;
    }

    private static void addRecipients(Email email, MimeMessage message) throws MessagingException {
        if (email.getRecipients() == null || email.getRecipients().size() == 0) {
            LOGGER.error("NoRecipientException occurred while sending email without specifying a recipient");
            throw new NoRecipientException("No Recipient found for email");
        }

        InternetAddress[] recipients = getInternetAddressesForEmail(email.getRecipients());
        message.addRecipients(Message.RecipientType.TO, recipients);

        if (email.getCarbonCopied() != null && email.getCarbonCopied().size() > 0) {
            InternetAddress[] carbonCopied = getInternetAddressesForEmail(email.getCarbonCopied());
            message.addRecipients(Message.RecipientType.CC, carbonCopied);
        }

        if (email.getBlindCarbonCopied() != null && email.getBlindCarbonCopied().size() > 0) {
            InternetAddress[] blindCarbonCopied = getInternetAddressesForEmail(email.getBlindCarbonCopied());
            message.addRecipients(Message.RecipientType.BCC, blindCarbonCopied);
        }
    }

    private static InternetAddress[] getInternetAddressesForEmail(List<String> emails) throws AddressException{
        return InternetAddress.parse(String.join(",", emails));
    }

    private static void setContent(Email email, MimeMessage mimeMessage) throws MessagingException, IOException {
        if (email.getEmailContent().isText()) {
            mimeMessage.setText(email.getEmailContent().getSource());
            return;
        }
        if (email.getEmailContent().isHtmlTemplate()) {
            String content = new String(
                    Files.readAllBytes(Paths.get(email.getEmailContent().getSource())));
            mimeMessage.setContent(content, CONTENT_TYPE_TEXT_HTML);
            return;
        }
    }
}
