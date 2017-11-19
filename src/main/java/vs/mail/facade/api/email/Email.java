package vs.mail.facade.api.email;

import java.util.List;

public class Email {
    private String sender;
    private String subject;
    private String content;
    private List<String> recipients;
    private List<String> carbonCopied;
    private List<String> blindCarbonCopied;
    private EmailContentType contentType;

    protected Email(String sender,
                    String subject,
                    String content,
                    List<String> recipients,
                    List<String> carbonCopied,
                    List<String> blindCarbonCopied,
                    EmailContentType contentType) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.recipients = recipients;
        this.carbonCopied = carbonCopied;
        this.blindCarbonCopied = blindCarbonCopied;
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", recipients=" + recipients +
                ", carbonCopied=" + carbonCopied +
                ", blindCarbonCopied=" + blindCarbonCopied +
                ", contentType=" + contentType +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public List<String> getCarbonCopied() {
        return carbonCopied;
    }

    public List<String> getBlindCarbonCopied() {
        return blindCarbonCopied;
    }

    public EmailContentType getContentType() {
        return contentType;
    }
}
