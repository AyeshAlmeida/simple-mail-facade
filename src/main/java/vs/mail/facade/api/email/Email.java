package vs.mail.facade.api.email;

import vs.mail.facade.api.email.content.EmailContent;

import java.util.List;

public class Email {
    private String sender;
    private String subject;
    private EmailContent emailContent;
    private List<String> recipients;
    private List<String> carbonCopied;
    private List<String> blindCarbonCopied;

    protected Email(String sender,
                    String subject,
                    EmailContent emailContent,
                    List<String> recipients,
                    List<String> carbonCopied,
                    List<String> blindCarbonCopied) {
        this.sender = sender;
        this.subject = subject;
        this.emailContent = emailContent;
        this.recipients = recipients;
        this.carbonCopied = carbonCopied;
        this.blindCarbonCopied = blindCarbonCopied;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", emailContent=" + emailContent +
                ", recipients=" + recipients +
                ", carbonCopied=" + carbonCopied +
                ", blindCarbonCopied=" + blindCarbonCopied +
                '}';
    }

    public String getSender() {
        return sender;
    }

    public String getSubject() {
        return subject;
    }

    public EmailContent getEmailContent() {
        return emailContent;
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
}
