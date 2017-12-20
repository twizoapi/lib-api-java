package com.twizo.services.verification;

import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.VerificationException;
import com.twizo.models.Verification;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonVerificationService extends AbstractService implements VerificationService {

    /**
     * Parse verification data received from the server to a Verification object
     *
     * @param data data to parse
     * @return Verification instance
     * @throws VerificationException   when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during json parsing
     */
    @Override
    public Verification parseVerification(String data) throws VerificationException, TwizoJsonParseException {
        if (data != null) {
            try {
                return gson.fromJson(data, Verification.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new VerificationException("Twizo didn't response as expected, please try again");
    }
}
