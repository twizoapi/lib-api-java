package com.twizo.models;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class Totp {

    /**
     * String, not null. Unique id for creating a new Totp
     */
    private String identifier;

    /**
     * String, not null. Issuer used to distribute Totp
     */
    private String issuer;

    /**
     * String, not null. Generated uri which can be converted to a QR code to get scanned by Twizo Authenticator
     */
    private String uri;

    /**
     * Verification, can be null. Verification linked to this Totp
     */
    private Verification verification;

    public String getIdentifier() {
        return identifier;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getUri() {
        return uri;
    }

    public Verification getVerification() {
        return verification;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setVerification(Verification verification) {
        this.verification = verification;
    }

    @Override
    public String toString() {
        return "Totp{" +
                "identifier='" + identifier + '\'' +
                ", issuer='" + issuer + '\'' +
                ", uri='" + uri + '\'' +
                ", verification=" + verification +
                '}';
    }
}
