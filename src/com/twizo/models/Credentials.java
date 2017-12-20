package com.twizo.models;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class Credentials {

    private String applicationTag;

    private Boolean isTestKey;

    public String getApplicationTag() {
        return applicationTag;
    }

    public Boolean isTestKey() {
        return this.isTestKey;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "applicationTag='" + applicationTag + '\'' +
                ", isTestKey=" + isTestKey +
                '}';
    }
}
