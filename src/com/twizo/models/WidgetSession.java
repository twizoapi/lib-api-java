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
public class WidgetSession {

  /**
   * String, not null. The unique id identifying this widget verification session.
   */
  private String sessionToken;

  /**
   * String, not null. The application used for sending the verification.
   */
  private String applicationTag;

  /**
   * Integer, can be null. The length of the token as specified when sending the verification or the
   * default value (6).
   */
  private Integer tokenLength;

  /**
   * String, can be null. The type of the token as specified when sending the verification or the
   * default value (numeric).
   */
  private String tokenType;

  /**
   * String, not null. The datetime the verification was received by the API. The datetime is in
   * ISO-8601 format.
   */
  private String createdDateTime;

  /**
   * Integer, can be null. The DCS value as specified when sending the verification.
   */
  private Integer dcs;

  /**
   * String, can be null. Currently not used yet.
   */
  private String language;

  /**
   * String, not null. The phone number as specified when sending the verification, the token must
   * be send to.
   */
  private String recipient;

  /**
   * String, can be null. The sender of the token in case of an SMS as specified when sending the
   * verification or the default value (Twizo).
   */
  private String sender;

  /**
   * Integer, can be null. The senderNpi of the token in case of an SMS as specified when sending
   * the verification or the automatically detected value by the API. See our tutorial ‘Sender’ for
   * more information.
   */
  private Integer senderNpi;

  /**
   * Integer, can be null. The senderNpi of the token in case of an SMS as specified when sending
   * the verification or the automatically detected value by the API. See our tutorial ‘Sender’ for
   * more information.
   */
  private Integer senderTon;

  /**
   * String, not null. The status of the widget verification session. The status and statusCode
   * fields are bound together and can have the following values:
   *
   * 0	No status: The widget verification session is created and waiting for the widget to get
   *    started and performing verifications.
   * 1	Success: The widget verification session is successfully verified. The entered token matches
   *    with the token sent to the phone.
   * 2	Expired: The widget verification session is expired as the token was not verified within the
   *    specified validity time of the widget verification session.
   * 3	Max_attempts: The verification is failed the maximum number of attempts of sending
   *    verification has reached.
   */
  private String status;

  /**
   * Integer, not null. See ‘status’ field for more information.
   */
  private Integer statusCode;

  /**
   * String, can be null. The tag as specified when sending the verification.
   */
  private String tag;

  /**
   * String, can be null. The tag value as specified when sending the verification or the default
   * value (Your verification token is: %token%)
   */
  private String bodyTemplate;

  /**
   * Array, not null. The allowed types as specified when creating the widget verification session.
   */
  private String[] requestedTypes;

  /**
   * Array, not null. The allowed types as specified when creating the widget verification session
   * minus types not available for the user. For example when you set ‘backupcode’ but for the
   * backupCodeIdentifier you specified the API cannot find backup codes, the type will be removed.
   */
  private String[] allowedTypes;

  /**
   * Integer, not null. The validity in seconds as specified when sending the verification or the
   * default value (60).
   */
  private Integer validity;

  /**
   * String, can be null. The backupCodeIdentifier if set when creating the session.
   */
  private String backupCodeIdentifier;

  /**
   * Array, not null. An array with verification ID’s performed during the session.
   */
  private String[] verificationIds;

  /**
   * Verification object, can be null. When the session is successful, this parameter contains the
   * successful verification object.
   */
  private Verification verification;

  public String getSessionToken() {
    return sessionToken;
  }

  public String getApplicationTag() {
    return applicationTag;
  }

  public Integer getTokenLength() {
    return tokenLength;
  }

  public String getTokenType() {
    return tokenType;
  }

  public String getCreatedDateTime() {
    return createdDateTime;
  }

  public Integer getDcs() {
    return dcs;
  }

  public String getLanguage() {
    return language;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getSender() {
    return sender;
  }

  public Integer getSenderNpi() {
    return senderNpi;
  }

  public Integer getSenderTon() {
    return senderTon;
  }

  public String getStatus() {
    return status;
  }

  public Integer getStatusCode() {
    return statusCode;
  }

  public String getTag() {
    return tag;
  }

  public String getBodyTemplate() {
    return bodyTemplate;
  }

  public String[] getRequestedTypes() {
    return requestedTypes;
  }

  public String[] getAllowedTypes() {
    return allowedTypes;
  }

  public Integer getValidity() {
    return validity;
  }

  public String getBackupCodeIdentifier() {
    return backupCodeIdentifier;
  }

  public String[] getVerificationIds() {
    return verificationIds;
  }

  public Verification getVerification() {
    return verification;
  }

  public void setVerification(Verification verification) {
    this.verification = verification;
  }

  @Override
  public String toString() {
    return "WidgetSession{" + "sessionToken='" + sessionToken + '\'' +
        ", applicationTag='" + applicationTag + '\'' +
        ", tokenLength=" + tokenLength +
        ", tokenType='" + tokenType + '\'' +
        ", createdDateTime='" + createdDateTime + '\'' +
        ", dcs=" + dcs +
        ", language='" + language + '\'' +
        ", recipient='" + recipient + '\'' +
        ", sender='" + sender + '\'' +
        ", senderNpi=" + senderNpi +
        ", senderTon=" + senderTon +
        ", status='" + status + '\'' +
        ", statusCode=" + statusCode +
        ", tag='" + tag + '\'' +
        ", bodyTemplate='" + bodyTemplate + '\'' +
        ", requestedTypes=" +
        (requestedTypes == null ? "null" : Arrays.asList(requestedTypes).toString()) +
        ", allowedTypes=" +
        (allowedTypes == null ? "null" : Arrays.asList(allowedTypes).toString()) +
        ", validity=" + validity +
        ", backupCodeIdentifier='" + backupCodeIdentifier + '\'' +
        ", verificationIds=" +
        (verificationIds == null ? "null" : Arrays.asList(verificationIds).toString()) +
        ", verification=" + verification +
        '}';
  }
}
