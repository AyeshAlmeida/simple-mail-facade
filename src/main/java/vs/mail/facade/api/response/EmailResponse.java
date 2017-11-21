package vs.mail.facade.api.response;

public class EmailResponse {
    private EmailStatus status;
    private String description;

    protected EmailResponse(EmailStatus status, String description) {
        this.status = status;
        this.description = description;
    }

    @Override
    public String toString() {
        return "EmailResponse{" +
                "status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public EmailStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
