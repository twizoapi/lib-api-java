package com.twizo.exceptions;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class TotpException extends TwizoException {

    /**
     * Constructor to give a custom message to the exception
     *
     * @param message Message which will be given to the super exception
     */
    public TotpException(String message) {
        super(message);
    }
}
