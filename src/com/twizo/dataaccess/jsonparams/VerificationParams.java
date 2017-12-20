package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationParams {

    /**
     * This is a mandatory string parameter. The recipient is a single phone number in international
     * format. The phone number can be a mobile number or a fixed phone number.
     */
    private String recipient;

    /**
     * This is an optional string parameter. The type defines how the token will be send to the phone.
     * Possible values are: ‘sms’ or ‘call’. The default value is ‘sms’.
     */
    private String type;

    /**
     * This is an optional integer parameter. The tokenLength defines the length of the token. The
     * minimum value is 4 and the maximum value 10. The default value is 6. When you set the
     * tokenLength, you have to set the tokenType as well.
     */
    private Integer tokenLength;

    /**
     * This is an optional string parameter. Possible values are ‘numeric’ and ‘alphanumeric’. Numeric
     * token will only contain the digits 0-9. For alphanumeric the token contains the digits 0-9 and
     * characters a-z (lowercase). The default value of the tokenType is ‘numeric’. When you set the
     * tokenType, you have to set the tokenLength as well.
     */
    private String tokenType;

    /**
     * This is an optional string parameter. The tag is a free text parameter you can use for your own
     * reference. The maximum length of the tag is 30 characters. The tag parameter is returned in the
     * result and you can use it for reporting purposes on your side.
     */
    private String tag;

    /**
     * This is an optional string parameter. If it is not set the API will automatically generate the
     * value. See ‘Verification session’ for more information. The maximum length of the sessionId is
     * 54 characters.
     */
    private String sessionId;

    /**
     * This is an optional integer parameter. The validity specifies how long the token is valid. The
     * validity is in seconds. If the token is not verified within this time, the token is expired.
     * The minimum value of the validity is 5 seconds and the maximum value is 3600 seconds (= 1
     * hour). The default value is 60 seconds.
     */
    private Integer validity;

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTokenLength() {
        return tokenLength;
    }

    public void setTokenLength(Integer tokenLength) {
        this.tokenLength = tokenLength;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }
}
