package com.twizo.models;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BiovoiceSubscription {

    /**
     * String, not null. The datetime the registration was received by the API. The datetime is in ISO-8601  format.
     */
    private String createdDateTime;

    /**
     * String, not null. The phone number as specified when sending the registration.
     */
    private String recipient;

    /**
     * String, not null. Sentence to register for biovoice
     */
    private String voiceSentence;

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getVoiceSentence() {
        return voiceSentence;
    }

    @Override
    public String toString() {
        return "BiovoiceSubscription{" +
                "createdDateTime='" + createdDateTime + '\'' +
                ", recipient='" + recipient + '\'' +
                ", voiceSentence='" + voiceSentence + '\'' +
                '}';
    }
}
