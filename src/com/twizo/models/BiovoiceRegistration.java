package com.twizo.models;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source
 * code
 *
 * (c) Twizo - info@twizo.com
 */
public class BiovoiceRegistration {

    /**
     * String, not null. The datetime the registration was received by the API. The datetime is in ISO-8601  format.
     */
    private String createdDateTime;

    /**
     * String, can be null. Language of registration
     */
    private String language;

    /**
     * Integer, can be null. When the registration is rejected or failed a reasonCode can be given explaining the cause
     * of the rejection. See further down below for an overview of possible reasonCodes
     */
    private Integer reasonCode;

    /**
     * String, not null. The phone number as specified when sending the registration.
     */
    private String recipient;

    /**
     * String, not null. Unique identifier of a registration
     */
    private String registrationId;

    /**
     * Float, can be null. The sales price of the registration.
     */
    private Float salesPrice;

    /**
     * String, can be null. The currency code of the salesPrice field. The value can be 'eur', 'usd' or 'sgd'. The
     * currency code is defined by the currency of your wallet.
     */
    private String salesPriceCurrencyCode;

    /**
     * String, not null. The status of the registration. The status and statusCode fields are bound together.
     */
    private String status;

    /**
     * Integer, not null. See 'status' field for more information.
     */
    private Integer statusCode;

    /**
     * String, not null. Sentence to register for biovoice
     */
    private String voiceSentence;

    /**
     * String, not null. Callback url for registration
     */
    private String webhook;

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getReasonCode() {
        return reasonCode;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public Float getSalesPrice() {
        return salesPrice;
    }

    public String getSalesPriceCurrencyCode() {
        return salesPriceCurrencyCode;
    }

    public String getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getVoiceSentence() {
        return voiceSentence;
    }

    public String getWebhook() {
        return webhook;
    }

    @Override
    public String toString() {
        return "BiovoiceRegistration{" +
                "createdDateTime='" + createdDateTime + '\'' +
                ", language='" + language + '\'' +
                ", reasonCode=" + reasonCode +
                ", recipient='" + recipient + '\'' +
                ", registrationId='" + registrationId + '\'' +
                ", salesPrice=" + salesPrice +
                ", salesPriceCurrencyCode='" + salesPriceCurrencyCode + '\'' +
                ", status='" + status + '\'' +
                ", statusCode=" + statusCode +
                ", voiceSentence='" + voiceSentence + '\'' +
                ", webhook='" + webhook + '\'' +
                '}';
    }
}
