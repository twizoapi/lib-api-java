package com.twizo.controllers.numberlookup;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.NumberLookupParams;
import com.twizo.exceptions.NumberLookupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.NumberLookup;
import com.twizo.services.numberlookup.JsonNumberLookupService;
import com.twizo.services.numberlookup.NumberLookupService;
import javafx.util.Pair;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiNumberLookupController extends TwizoController implements
        NumberLookupController {

    /**
     * NumberLookupController instance to parse server results
     */
    private final NumberLookupService numberLookupService;

    /**
     * Create a new ApiNumberLookupController instance
     *
     * @param worker Api worker
     */
    public ApiNumberLookupController(Worker worker) {
        super(worker);

        numberLookupService = new JsonNumberLookupService();
    }

    /**
     * Create a new NumberLookup and parse the results to a NumberLookup array
     *
     * @param params NumberLookupParams object to add parameters to the request
     * @return Array of NumberLookups with data received after the request
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    @Override
    public NumberLookup[] createNumberlookup(NumberLookupParams params) throws TwizoCallException,
            TwizoJsonParseException, NumberLookupException {
        return numberLookupService.parseNewNumberLookup(
                worker.execute("numberlookup/submit", processParams(params), RequestType.POST));
    }

    /**
     * Create a new simple NumberLookup by providing only the phone number
     *
     * @param phoneNumber phone number to lookup
     * @return result of the NumberLookup
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    @Override
    public NumberLookup createSimpleNumberLookup(String phoneNumber) throws TwizoCallException, TwizoJsonParseException,
            NumberLookupException {
        NumberLookupParams params = new NumberLookupParams();
        params.setNumbers(new String[]{phoneNumber});

        return numberLookupService.parseNewNumberLookup(
                worker.execute("numberlookup/submit", processParams(params), RequestType.POST))[0];
    }

    /**
     * Get the current status of a numberLookup by using it's messageId
     *
     * @param messageId id of the message
     * @return Found NumberLookup instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    @Override
    public NumberLookup getNumberLookup(String messageId) throws TwizoCallException, TwizoJsonParseException,
            NumberLookupException {
        return numberLookupService.parseNumberLookup(worker
                .execute(String.format("numberlookup/submit/%s", messageId), null, RequestType.GET));
    }

    /**
     * Poll results about NumberLookup data. Only possible when resultType is set to 1 or 3.
     *
     * @return Array of NumberLookups
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws NumberLookupException   when something goes wrong during the process
     */
    @Override
    public NumberLookup[] getResults() throws TwizoCallException, TwizoJsonParseException, NumberLookupException {
        Pair<String, NumberLookup[]> numberLookupResults = numberLookupService
                .parseResultArray(worker.execute("numberlookup/poll", null, RequestType.GET));

        // Delete the delivery reports by using the batchId
        worker.execute(String.format("numberlookup/poll/%s", numberLookupResults.getKey()), null,
                RequestType.DELETE);

        return numberLookupResults.getValue();
    }

    /**
     * This method increases the chance the parameters added to the creation of a NumberLookup are
     * correct and properly formatted
     *
     * @param params Parameters which will be added to the request
     * @return Properly formatted JSON String with parameters
     * @throws NumberLookupException   when parameters are invalid
     * @throws TwizoJsonParseException when something goes wrong when parsing JSON
     */
    private String processParams(NumberLookupParams params) throws NumberLookupException, TwizoJsonParseException {
        if (params != null) {

            String[] phoneNumbers = params.getNumbers();

            // Check if phone number is properly formatted
            for (int i = 0; i < phoneNumbers.length; i++) {
                if (phoneNumbers[i] != null) {
                    phoneNumbers[i] = processPhoneNumber(phoneNumbers[i]);

                    if (phoneNumbers[i].matches("[0-9]")) {
                        throw new NumberLookupException("A phone number can only contain digits");
                    }
                } else {
                    throw new NumberLookupException("Make sure there a no NULL entities in the Number Array");
                }
            }

            try {
                return gson.toJson(params);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        } else {
            return null;
        }
    }
}