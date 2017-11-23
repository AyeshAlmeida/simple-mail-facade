package vs.mail.facade.api.email;

import vs.mail.facade.api.email.content.EmailContent;

import java.util.List;

public class EmailBuilder {
    private String sender;
    private String subject;
    private EmailContent emailContent;
    private List<String> recipients;
    private List<String> carbonCopied;
    private List<String> blindCarbonCopied;

    public EmailBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder setEmailContent(EmailContent emailContent) {
        this.emailContent = emailContent;
        return this;
    }

    public EmailBuilder setRecipients(List<String> recipients) {
        this.recipients = recipients;
        return this;
    }

    public EmailBuilder setCarbonCopied(List<String> carbonCopied) {
        this.carbonCopied = carbonCopied;
        return this;
    }

    public EmailBuilder setBlindCarbonCopied(List<String> blindCarbonCopied) {
        this.blindCarbonCopied = blindCarbonCopied;
        return this;
    }

    public Email createEmail() {
        return new Email(sender, subject, emailContent, recipients, carbonCopied, blindCarbonCopied);
    }
}