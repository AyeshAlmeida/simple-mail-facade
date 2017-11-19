package vs.mail.facade.api.config;

public class ConfigurationBuilder {
    private String username;
    private String password;
    private String smtpHost;
    private SecType secType;
    private boolean smtpDebugEnable;

    public ConfigurationBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public ConfigurationBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public ConfigurationBuilder setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
        return this;
    }

    public ConfigurationBuilder setSecType(SecType secType) {
        this.secType = secType;
        return this;
    }

    public ConfigurationBuilder setSmtpDebugEnable(boolean smtpDebugEnable) {
        this.smtpDebugEnable = smtpDebugEnable;
        return this;
    }

    public Configuration createConfiguration() {
        return new Configuration(username, password, smtpHost, secType, smtpDebugEnable);
    }
}