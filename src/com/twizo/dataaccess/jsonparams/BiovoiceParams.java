package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BiovoiceParams {

    /**
     * Language which is used for Biovoice registration. This parameter is not mandatory
     */
    private String language;

    /**
     * This is a mandatory string parameter. The recipient is a single phone number in international format.
     * The phone number can be a mobile number or a fixed phone number.
     */
    private String recipient;

    /**
     * Webhook vor registration. This parameter is not mandatory
     */
    private String webHook;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getWebHook() {
        return webHook;
    }

    public void setWebHook(String webHook) {
        this.webHook = webHook;
    }
}
