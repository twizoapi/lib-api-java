package com.twizo.services.application;

import com.twizo.exceptions.ApplicationException;
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
public interface ApplicationService {

    /**
     * Parse received information to a Credentials object
     *
     * @param data data which has to be parsed
     * @return Credentials object
     * @throws ApplicationException    when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    Credentials parseCredentials(String data) throws TwizoJsonParseException, ApplicationException;

    /**
     * Parse received information to a String array of allowed types *
     *
     * @param data data which has to be parsed
     * @return String array object
     * @throws ApplicationException    when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    String[] parseAllowedTypes(String data) throws TwizoJsonParseException, ApplicationException;
}
