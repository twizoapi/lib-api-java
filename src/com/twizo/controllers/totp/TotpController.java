package com.twizo.controllers.totp;

import com.twizo.dataaccess.jsonparams.TotpParams;
import com.twizo.exceptions.TotpException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Totp;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface TotpController {

    /**
     * Create a new Totp and parse the result to a Totp object
     *
     * @param params TotpParams object to add parameters to the request
     * @return Totp instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    Totp createTotp(TotpParams params) throws TwizoCallException, TwizoJsonParseException, TotpException;

    /**
     * Verify a Totp token
     *
     * @param identifier unique identifier of a Totp session
     * @param token      verification token
     * @return Totp token
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    Totp verifyTotp(String identifier, String token) throws TwizoCallException, TwizoJsonParseException, TotpException;

    /**
     * Get the status of a Totp and parse the result to a Totp object
     *
     * @param identifier unique id of Totp
     * @return Totp instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    Totp getTotpStatus(String identifier) throws TwizoCallException, TwizoJsonParseException, TotpException;

    /**
     * Delete a Totp instance from Twizo's servers
     *
     * @param identifier unique id of Totp
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    void deleteTotp(String identifier) throws TwizoCallException, TwizoJsonParseException, TotpException;

}
