package vs.mail.facade.api.email.address;

import vs.mail.facade.exception.EmailAddressException;

public class EmailAddress {
    private static final String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String address;

    public EmailAddress(String address) throws EmailAddressException{
        if (checkValidity(address))
            this.address = address;
        else
            throw new EmailAddressException("Invalid Email Address");
    }

    public String getAddress() {
        return address;
    }

    private static boolean checkValidity(String email) {
        return emailRegex.matches(email);
    }
}