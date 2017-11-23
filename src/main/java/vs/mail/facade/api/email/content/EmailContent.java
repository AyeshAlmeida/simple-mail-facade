package vs.mail.facade.api.email.content;

public class EmailContent {
    private EmailContentType contentType;
    private String source;

    public EmailContent(EmailContentType contentType, String source) {
        this.contentType = contentType;
        this.source = source;
    }

    @Override
    public String toString() {
        return "EmailContent{" +
                "contentType=" + contentType +
                ", source='" + source + '\'' +
                '}';
    }

    public boolean isText(){
        return contentType.equals(EmailContentType.TEXT);
    }

    public boolean isHtmlTemplate(){
        return contentType.equals(EmailContentType.HTML);
    }

    public EmailContentType getContentType() {
        return contentType;
    }

    public String getSource() {
        return source;
    }
}
