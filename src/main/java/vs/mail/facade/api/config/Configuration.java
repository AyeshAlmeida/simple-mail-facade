package vs.mail.facade.api.config;

public class Configuration {
    private String username;
    private String password;
    private String smtpHost;
    private SecType secType;
    private boolean smtpDebugEnable;

    protected Configuration(String username,
                            String password,
                            String smtpServerAddress,
                            SecType secType,
                            boolean smtpDebugEnable) {
        this.username = username;
        this.password = password;
        this.smtpHost = smtpServerAddress;
        this.secType = secType;
        this.smtpDebugEnable = smtpDebugEnable;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", smtpHost='" + smtpHost + '\'' +
                ", secType=" + secType +
                ", smtpDebugEnable=" + smtpDebugEnable +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public SecType getSecType() {
        return secType;
    }

    public boolean isSmtpDebugEnable() {
        return smtpDebugEnable;
    }
}
