package com.twizo.services.totp;

import com.twizo.exceptions.TotpException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Totp;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface TotpService {

    /**
     * Parse received information to a Totp object
     *
     * @param data data which has to get parsed
     * @return Totp object
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    Totp parseTotp(String data) throws TwizoJsonParseException, TotpException;

    /**
     * Parse received information to a Totp object
     *
     * @param data data which has to get parsed
     * @return Totp object
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    Totp parseVerification(String data) throws TwizoJsonParseException, TotpException;
}
