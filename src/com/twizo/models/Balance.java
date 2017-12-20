package com.twizo.models;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class Balance {

    /**
     * Float, not null. The current credit application of your wallet. Can be negative in certain
     * situations.
     */
    private Float credit;

    /**
     * String, not null. This is a 3-digit ISO 4217 code of the currency of your wallet.
     */
    private String currencyCode;

    /**
     * Integer, not null. The number of free verifications you have left for this month.
     */
    private Integer freeVerifications;

    /**
     * String, not null. The name of your wallet.
     */
    private String wallet;

    public Float getCredit() {
        return credit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Integer getFreeVerifications() {
        return freeVerifications;
    }

    public String getWallet() {
        return wallet;
    }

    @Override
    public String toString() {
        return "Balance{" + "credit=" + credit +
                ", currencyCode='" + currencyCode + '\'' +
                ", freeVerifications=" + freeVerifications +
                ", wallet='" + wallet + '\'' +
                '}';
    }
}
