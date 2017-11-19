package vs.mail.facade.api.response;

public class EmailResponse {
    private EmailStatus status;
    private String description;

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

    public void setStatus(EmailStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
