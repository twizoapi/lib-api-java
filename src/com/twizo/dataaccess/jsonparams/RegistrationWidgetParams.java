package com.twizo.dataaccess.jsonparams;

public class RegistrationWidgetParams {

    /**
     * This is a optional array parameter. The allowedTypes defines which verification types the
     * user can register for in the widget. Possible values are: ‘sms’, ‘call’, 'biovoice', 'push',
     * 'totp', 'telegram', 'line' or 'backupcode'. When you do not set the parameter, the widget
     * will use as default the types you have set for your account (Open the settings of the
     * account and on the Verification tab select the types you want to allow your users to use).
     */
    private String[] allowedTypes;

    /**
     * This is a mandatory string parameter when for the allowedTypes 'sms' or 'call' is included.
     * The recipient is a single phone number in international format. The phone number can be a
     * mobile number or a fixed phone number.
     */
    private String recipient;

    /**
     * This is a mandatory string parameter when for the allowedTypes 'backupcode' is included.
     * The backupCodeIdentifier is the identifier you used for creating the backup codes for the user.
     */
    private String backupCodeIdentifier;

    /**
     * This is a mandatory string parameter when for the allowedTypes 'totp' is included. The
     * totpIdentifier is the identifier you used for creating the TOTP for the user.
     */
    private String totpIdentifier;

    /**
     * This is a mandatory string parameter when you want to use a verification of type 'push',
     * 'telegram' or 'line'. The issuer is the name of the site the user wants to login to.
     * When the user receives the request to confirm he wants to login, the user will get a message
     * like "Please confirm to verify your login of '<issuer>'".
     */
    private String issuer;

    public String[] getAllowedTypes() {
        return allowedTypes;
    }

    public void setAllowedTypes(String[] allowedTypes) {
        this.allowedTypes = allowedTypes;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBackupCodeIdentifier() {
        return backupCodeIdentifier;
    }

    public void setBackupCodeIdentifier(String backupCodeIdentifier) {
        this.backupCodeIdentifier = backupCodeIdentifier;
    }

    public String getTotpIdentifier() {
        return totpIdentifier;
    }

    public void setTotpIdentifier(String totpIdentifier) {
        this.totpIdentifier = totpIdentifier;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
