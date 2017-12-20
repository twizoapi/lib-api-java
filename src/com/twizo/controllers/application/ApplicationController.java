package com.twizo.controllers.application;

import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Balance;
import com.twizo.models.Credentials;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface ApplicationController {

    /**
     * Get details about the entered API key
     *
     * @return Credentials object containing information about the key
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws ApplicationException    when credentials are invalid
     */
    Credentials verifyCredentials() throws TwizoCallException, TwizoJsonParseException, ApplicationException;

    /**
     * Get allowed types for a specific API key
     *
     * @return String of allowed types
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws ApplicationException    when something goes wrong during the process
     */
    String[] getAllowedTypes() throws TwizoCallException, TwizoJsonParseException, ApplicationException;
}
