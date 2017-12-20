package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationSmsParams extends VerificationParams {

    /**
     * This is an optional string parameter. The body template in case an SMS needs to be send. The
     * body template must contain the tag ‘%token%’ which will be used by the system to replace it
     * with the actual token to be send to the end user. The maximum length on the body template is
     * for GSM-7 alphabet 160 characters and for Unicode maximum 70 characters. This is including the
     * token which will be inserted by the system. So if you set a tokenLength of 8, the max length of
     * the bodyTemplate is 160-8=152 characters excluding the text ‘%token%’. If you do not set the
     * tokenLength, the API will need to consider the maximum length of the token (10) for the maximum
     * bodyTemplate length, so in that case the bodyTemplate can have a maximum length of 150
     * characters for GSM-7 and 60 for Unicode. Concatenated messages are not possible for these SMS
     * messages. See our tutorial ‘Unicode’ for more information on the maximum length of the body.
     * Default value for the bodyTemplate is: Your verification token is: %token%
     */
    private String bodyTemplate;

    /**
     * This is an optional string parameter. The sender is what the receiver of the SMS see as the
     * submitter of the SMS. See our tutorial ‘Sender’ for more information. The default value for the
     * sender is ‘Twizo’.
     */
    private String sender;

    /**
     * This is an optional integer parameter. If it is not set the API will automatically detect the
     * value. See our tutorial ‘Sender’ for more information.
     */
    private Integer senderTon;

    /**
     * This is an optional integer parameter. If it is not set the API will automatically detect the
     * value. See our tutorial ‘Sender’ for more information.
     */
    private Integer senderNpi;

    /**
     * This is an optional integer parameter. With the DCS parameter you can specify the character set
     * of the bodyTemplate. For GSM-7 the value should be 0 and for Unicode the value should be 8. The
     * default value is 0. See our tutorial ‘Unicode’ for more information.
     */
    private Integer dcs;

    public String getBodyTemplate() {
        return bodyTemplate;
    }

    public void setBodyTemplate(String bodyTemplate) {
        this.bodyTemplate = bodyTemplate;
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

    public Integer getDcs() {
        return dcs;
    }

    public void setDcs(Integer dcs) {
        this.dcs = dcs;
    }
}
