package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class TotpParams {

    /**
     * This is a mandatory string parameter. The recipient is a single phone number in international format.
     * The phone number can be a mobile number or a fixed phone number.
     */
    private String identifier;

    /**
     * This is a mandatory string parameter. Issuer to distribute Totp
     */
    private String issuer;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
}
