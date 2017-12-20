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
public class BackupCodeResult {

    /**
     * The identifier you used to generate the backup codes for the user.
     */
    private String identifier;

    /**
     * Amount of backup codes which can still be used
     */
    private Integer amountOfCodesLeft;

    /**
     * String array containing backup codes
     */
    private String[] codes;

    /**
     * String, not null. The datetime the verification was received by the API. The datetime is in
     * ISO-8601 format.
     */
    private String createdDateTime;

    /**
     * Verification linked to the backup code
     */
    private Verification verification;

    public String getIdentifier() {
        return identifier;
    }

    public Integer getAmountOfCodesLeft() {
        return amountOfCodesLeft;
    }

    public String[] getCodes() {
        return codes;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setAmountOfCodesLeft(Integer amountOfCodesLeft) {
        this.amountOfCodesLeft = amountOfCodesLeft;
    }

    @Override
    public String toString() {
        return "BackupCodeResult{" + "identifier='" + identifier + '\'' +
                ", amountOfCodesLeft=" + amountOfCodesLeft +
                ", codes=" + (codes == null ? "null" : Arrays.asList(codes).toString()) +
                ", createdDateTime='" + createdDateTime + '\'' +
                ", verification=" + verification +
                '}';
    }
}