package vs.mail.facade.api.response;

public class EmailResponseBuilder {
    private EmailStatus status;
    private String description;

    public EmailResponseBuilder setStatus(EmailStatus status) {
        this.status = status;
        return this;
    }

    public EmailResponseBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public EmailResponse createEmailResponse() {
        return new EmailResponse(status, description);
    }
}