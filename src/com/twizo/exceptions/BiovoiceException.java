package com.twizo.exceptions;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BiovoiceException extends TwizoException {

    /**
     * Constructor to rethrow an exception
     *
     * @param exception Exception which will be rethrown
     */
    public BiovoiceException(Exception exception) {
        super(exception);
    }
}
