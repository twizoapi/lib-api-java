package com.twizo.models;

import java.util.Arrays;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class RegistrationWidget {

    /**
     * String, not null. The unique id identifying this widget verification session.
     */
    private String sessionToken;

    /**
     * String, not null. The application used for sending the verification.
     */
    private String applicationTag;

    /**
     * Array, not null. The allowed types as specified when creating the widget verification session.
     */
    private String[] requestedTypes;

    /**
     *
     */
    private String[] registeredTypes;

    /**
     * Array, not null. The allowed types as specified when creating the widget registration
     * minus types not available for the user. For example when you set ‘backupcode’ but for the
     * backupCodeIdentifier you specified the API cannot find backup codes, the type will be removed.
     */
    private String[] allowedTypes;

    /**
     * String, not null. The phone number as specified when sending the verification, the token must
     * be send to.
     */
    private String recipient;

    /**
     *
     */
    private String totpIdentifier;

    /**
     * String, can be null. The backupCodeIdentifier if set when creating the session.
     */
    private String backupCodeIdentifier;

    /**
     *
     */
    private String issuer;

    /**
     * String, can be null. Currently not used yet.
     */
    private String language;

    /**
     *
     */
    private String status;

    /**
     * Integer, not null. See ‘status’ field for more information.
     */
    private int statusCode;

    /**
     * String, not null. The datetime the verification was received by the API. The datetime is in
     * ISO-8601 format.
     */
    private String createdDateTime;

    public String getSessionToken() {
        return sessionToken;
    }

    public String getApplicationTag() {
        return applicationTag;
    }

    public String[] getRequestedTypes() {
        return requestedTypes;
    }

    public String[] getRegisteredTypes() {
        return registeredTypes;
    }

    public String[] getAllowedTypes() {
        return allowedTypes;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getTotpIdentifier() {
        return totpIdentifier;
    }

    public String getBackupCodeIdentifier() {
        return backupCodeIdentifier;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getLanguage() {
        return language;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    @Override
    public String toString() {
        return "RegistrationWidget{" +
                "sessionToken='" + sessionToken + "'," +
                "applicationTag='" + applicationTag + "'," +
                "requestedTypes=" + (requestedTypes == null ? "null" : Arrays.asList(requestedTypes).toString()) + "," +
                "registeredTypes=" + (registeredTypes == null ? "null" : Arrays.asList(registeredTypes).toString()) + "," +
                "allowedTypes=" + (allowedTypes == null ? "null" : Arrays.asList(allowedTypes).toString()) + "," +
                "recipient='" + recipient + "'," +
                "totpIdentifier='" + totpIdentifier + "'," +
                "backupCodeIdentifier='" + backupCodeIdentifier + "'," +
                "issuer='" + issuer + "'," +
                "language='" + language + "'," +
                "status" + status + "'," +
                "statusCode='" + statusCode + "'," +
                "createdDateTime='" + createdDateTime + "'" +
                "}";
    }

}
