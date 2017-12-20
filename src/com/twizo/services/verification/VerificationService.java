package com.twizo.services.verification;

import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.VerificationException;
import com.twizo.models.Verification;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface VerificationService {

    /**
     * Parse verification data received from the server to a Verification object
     *
     * @param data data to parse
     * @return Verification instance
     * @throws VerificationException   when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during json parsing
     */
    Verification parseVerification(String data) throws VerificationException, TwizoJsonParseException;

}
