package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsParams {

    /**
     * This is a mandatory array with strings parameter. It should be an array of numbers (in string
     * format), in international format, for the SMS. At least 1 number must be set and maximum 1000.
     */
    private String[] recipients;

    /**
     * This is a mandatory string parameter. The body of the SMS. The API will automatically determine
     * if the body is Unicode or not and when the body is too long for a single SMS the API will
     * automatically split up the body into multiple parts. The maximum number of concatenated parts
     * is 9
     */
    private String body;

    /**
     * This is a mandatory string parameter. The sender is what the receiver of the SMS see as the
     * submitter of the SMS. When it is numeric the maximum length is 17 digits, when it is
     * alphanumeric the maximum length is 11.
     */
    private String sender;

    /**
     * This is an optional integer parameter. If it is not set the API will automatically detect the
     * value.
     */
    private Integer senderTon;

    /**
     * This is an optional integer parameter. If it is not set the API will automatically detect the
     * value.
     */
    private Integer senderNpi;

    /**
     * This is an optional integer parameter. Can be used to sendSimple hidden SMS. Allowed values:
     * 0-255.
     */
    private Integer pid;

    /**
     * This is an optional string parameter. Datetime of scheduled delivery. Must be in ISO-8601
     * format, example: 2016-10-31T12:34:56Z
     */
    private String scheduledDelivery;

    /**
     * This is an optional string parameter. The tag is a free text parameter you can use for your own
     * reference. The maximum length of the tag is 30 characters. The tag parameter is returned in the
     * result and you can use it for reporting purposes on your side
     */
    private String tag;

    /**
     * Optional integer parameter for advanced, can be used to mark body as Unicode (8). Allowed
     * values: 0-255
     */
    private Integer dcs;

    /**
     * This is an optional integer parameter. The validity specifies how long the message is valid.
     * The validity is in seconds. If the message could not be delivered within this time, the message
     * will expire and no more attempts will be made to deliver it. The minimum value of the validity
     * is 5 seconds and the maximum value is 259200 seconds (= 72 hours). The default value is 259200
     * seconds.
     */
    private Integer validity;

    /**
     * This is an optional integer parameter. If you want to receive or poll for final results of your
     * verifications, you can set the resultType. You can use the results for your own reporting
     * purposes. Possible values of the resultType are:
     *
     * 0:	No results (default)
     * 1:	Callback (you have to specify the callbackUrl)
     * 2:	Polling
     * 3:	Callback & polling (you have to specify the callbackUrl)
     */
    private Integer resultType;

    /**
     * This string parameter is only mandatory when resultType is set to 1 or 3. When the callbackUrl
     * is set, this URL will be used by our system to submit status updates to you. This parameter is
     * only allowed when the resultType parameter is set to 1 or 3.
     */
    private String callbackUrl;

    /**
     * Optional string parameter for advanced; sets concat message. Can only consist of hexadecimal
     * characters. Length in this parameter divided by 2 is subtracted from the maximum body length.
     */
    private String udh;

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getSenderTon() {
        return senderTon;
    }

    public void setSenderTon(Integer senderTon) {
        this.senderTon = senderTon;
    }

    public Integer getSenderNpi() {
        return senderNpi;
    }

    public void setSenderNpi(Integer senderNpi) {
        this.senderNpi = senderNpi;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getScheduledDelivery() {
        return scheduledDelivery;
    }

    public void setScheduledDelivery(String scheduledDelivery) {
        this.scheduledDelivery = scheduledDelivery;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getDcs() {
        return dcs;
    }

    public void setDcs(Integer dcs) {
        this.dcs = dcs;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getUdh() {
        return udh;
    }

    public void setUdh(String udh) {
        this.udh = udh;
    }
}
