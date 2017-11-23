package vs.mail.facade.util;

import vs.mail.facade.api.email.Email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static vs.mail.facade.property.DefaultPropertyValues.CONTENT_TYPE_TEXT_HTML;

public final class MimeMessageHelper {
    public static MimeMessage getMessage(Email email, Session session) throws MessagingException, IOException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(email.getSender()));
        InternetAddress[] recipients = InternetAddress.parse(String.join(",", email.getRecipients()));
        message.addRecipients(Message.RecipientType.TO, recipients);
        message.setSubject(email.getSubject());
        setContent(email, message);
        return message;
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
