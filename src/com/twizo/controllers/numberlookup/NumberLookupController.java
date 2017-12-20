package com.twizo.controllers.numberlookup;

import com.twizo.dataaccess.jsonparams.NumberLookupParams;
import com.twizo.exceptions.NumberLookupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.NumberLookup;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface NumberLookupController {

    /**
     * Create a new NumberLookup and parse the results to a NumberLookup array
     *
     * @param params NumberLookupParams object to add parameters to the request
     * @return Array of NumberLookups with data received after the request
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    NumberLookup[] createNumberlookup(NumberLookupParams params) throws TwizoCallException, TwizoJsonParseException, NumberLookupException;

    /**
     * Create a new simple NumberLookup by providing only the phone number
     *
     * @param phoneNumber phone number to lookup
     * @return result of the NumberLookup
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    NumberLookup createSimpleNumberLookup(String phoneNumber) throws TwizoCallException, TwizoJsonParseException, NumberLookupException;

    /**
     * Get the current status of a numberLookup by using it's messageId
     *
     * @param messageId id of the message
     * @return Found NumberLookup instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    NumberLookup getNumberLookup(String messageId) throws TwizoCallException, TwizoJsonParseException, NumberLookupException;

    /**
     * Poll results about NumberLookup data. Only possible when resultType is set to 1 or 3.
     *
     * @return Array of NumberLookups
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    NumberLookup[] getResults() throws TwizoCallException, TwizoJsonParseException, NumberLookupException;
}
