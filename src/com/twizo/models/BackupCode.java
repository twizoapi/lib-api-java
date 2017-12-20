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
public class BackupCode {

    /**
     * Unique identifier of the user
     */
    private String identifier;

    /**
     * Amount of backup codes left for a specific user
     */
    private Integer amountOfCodesLeft;

    /**
     * String array which contains remaining backup codes
     */
    private String[] codes;

    /**
     * String which tells when backup codes were created
     */
    private String createdDateTime;

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

    @Override
    public String toString() {
        return "BackupCode{" + "identifier='" + identifier + '\'' +
                ", amountOfCodesLeft=" + amountOfCodesLeft +
                ", codes=" + Arrays.toString(codes) +
                ", createdDateTime='" + createdDateTime + '\'' +
                '}';
    }
}
