package com.twizo.controllers.numberlookup;

import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.jsonparams.NumberLookupParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.NumberLookup;
import com.twizo.services.numberlookup.NumberLookupServiceFactory;
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
class ApiNumberLookupController extends TwizoController implements
    NumberLookupController {

  /**
   * NumberLookupController instance to parse server results
   */
  private final NumberLookupService numberLookupService;

  /**
   * Create a new NumberLookup instance
   *
   * @param apiHost Preferred API Node
   * @param apiKey Accesskey to the API
   */
  ApiNumberLookupController(String apiHost, String apiKey) {
    super(apiHost, apiKey);

    NumberLookupServiceFactory numberLookupControllerFactory = new NumberLookupServiceFactory();
    numberLookupService = numberLookupControllerFactory.getInstance();
  }

  /**
   * Create a new NumberLookup and parse the results to a NumberLookup array
   *
   * @param params NumberLookupParams object to add parameters to the request
   * @return Array of NumberLookups with data received after the request
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public NumberLookup[] createNumberlookup(NumberLookupParams params) throws TwizoException {
    return numberLookupService.parseNewNumberLookup(
        worker.execute("numberlookup/submit", processParams(params), RequestType.POST));
  }

  /**
   * Create a new simple NumberLookup by providing only the phone number
   *
   * @param phoneNumber phone number to lookup
   * @return result of the NumberLookup
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public NumberLookup createSimpleNumberLookup(String phoneNumber) throws TwizoException {
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
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public NumberLookup getNumberLookup(String messageId) throws TwizoException {
    return numberLookupService.parseNumberLookup(worker
        .execute(String.format("numberlookup/submit/%s", messageId), null, RequestType.GET));
  }

  /**
   * Poll results about NumberLookup data. Only possible when resultType is set to 1 or 3.
   *
   * @return Array of NumberLookups
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public NumberLookup[] getResults() throws TwizoException {
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
   */
  private String processParams(NumberLookupParams params) throws TwizoException {
    if (params != null) {

      String[] phoneNumbers = params.getNumbers();

      // Check if phone number is properly formatted
      for (int i = 0; i < phoneNumbers.length; i++) {
        if (phoneNumbers[i] != null) {
          phoneNumbers[i] = processPhoneNumber(phoneNumbers[i]);

          if (phoneNumbers[i].matches("[0-9]")) {
            throw new TwizoException("A phone number can only contain digits");
          }
        } else {
          throw new TwizoException("Make sure there a no NULL entities in the Number Array");
        }
      }

      return gson.toJson(params);
    } else {
      return null;
    }
  }
}