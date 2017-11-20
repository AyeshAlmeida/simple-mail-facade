package vs.mail.facade.api.email;

import java.util.List;

import static vs.mail.facade.util.EmailBuilderHelper.getUnmodifiableListForData;

public class EmailBuilder {
    private String sender;
    private String subject;
    private String content;
    private List<String> recipients;
    private List<String> carbonCopied;
    private List<String> blindCarbonCopied;
    private EmailContentType contentType;

    public EmailBuilder setSender(String sender) {
        this.sender = sender;
        return this;
    }

    public EmailBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailBuilder setContent(String content) {
        this.content = content;
        return this;
    }

    public EmailBuilder setRecipients(List<String> recipients) {
        this.recipients = getUnmodifiableListForData(recipients);
        return this;
    }

    public EmailBuilder setCarbonCopied(List<String> carbonCopied) {
        this.carbonCopied = getUnmodifiableListForData(carbonCopied);
        return this;
    }

    public EmailBuilder setBlindCarbonCopied(List<String> blindCarbonCopied) {
        this.blindCarbonCopied = getUnmodifiableListForData(blindCarbonCopied);
        return this;
    }

    public EmailBuilder setContentType(EmailContentType contentType) {
        this.contentType = contentType;
        return this;
    }

    public Email createEmail() {
        return new Email(sender, subject, content, recipients, carbonCopied, blindCarbonCopied, contentType);
    }
}