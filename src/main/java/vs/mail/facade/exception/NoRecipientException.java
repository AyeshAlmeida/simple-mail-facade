package vs.mail.facade.exception;

public class NoRecipientException extends RuntimeException{
    public NoRecipientException(String message) {
        super(message);
    }
}
