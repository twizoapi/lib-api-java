package com.twizo.controllers.widget;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public enum WidgetSessionType {

    /**
     * Verification is done by sms or call
     */
    RECIPIENT,

    /**
     * Verification is done by backup code
     */
    BACKUPCODE,

    /**
     * Verification is done by both
     */
    BOTH

}
