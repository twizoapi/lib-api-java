package com.twizo.services.numberlookup;

import com.twizo.exceptions.NumberLookupException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.NumberLookup;
import javafx.util.Pair;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface NumberLookupService {

    /**
     * Parse creation of NumberLookup results to a NumberLookup array
     *
     * @param data data which has to be parsed
     * @return Array of NumberLookups with data received after the request
     * @throws TwizoJsonParseException when something goes wrong during the process
     * @throws NumberLookupException   when something goes wrong during parsing the JSON
     */
    NumberLookup[] parseNewNumberLookup(String data) throws TwizoJsonParseException, NumberLookupException;

    /**
     * Parse the data result of status request to a NumberLookup instance
     *
     * @param data data which has to be parsed
     * @return Instance of NumberLookup received after doing the GET request to get information about
     * numberLookups
     * @throws TwizoJsonParseException when something goes wrong during the process
     * @throws NumberLookupException   when something goes wrong during parsing the JSON
     */
    NumberLookup parseNumberLookup(String data) throws TwizoJsonParseException, NumberLookupException;

    /**
     * Parse polling results to a NumberLookup array
     *
     * @param data data which has to be parsed
     * @return Array of NumberLookups
     * @throws TwizoJsonParseException when something goes wrong during the process
     * @throws NumberLookupException   when something goes wrong during parsing the JSON
     */
    Pair<String, NumberLookup[]> parseResultArray(String data) throws TwizoJsonParseException, NumberLookupException;
}
