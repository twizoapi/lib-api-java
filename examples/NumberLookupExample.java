import com.twizo.Twizo;
import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.dataaccess.jsonparams.NumberLookupParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.NumberLookup;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class NumberLookupExample {

  private final NumberLookupController numberLookupController;

  NumberLookupExample(Twizo twizo) {

    // Get a new NumberLookupController instance
    numberLookupController = twizo.getNumberLookupController();
  }

  /**
   * This example shows how to create a new NumberLookup
   *
   * @param phoneNumber phone number to lookup
   * @return Array of NumberLookup instances
   * @throws TwizoException when something goes wrong during the process
   */
  NumberLookup[] createNumberLookup(String phoneNumber) throws TwizoException {
    // Create a NumberLookupParams instance to pass parameters to the request
    NumberLookupParams params = new NumberLookupParams();

    // Add the phone numbers to the request, this is required
    params.setNumbers(new String[]{phoneNumber});

    // Add a tag to the request, this is optional
    params.setTag("Twizo");

    // Set the validity of the request, this is optional
    params.setValidity(259200);

    // Set the ResultType of the request, optional. When not filled in, 0 is filled in as
    // default value
    params.setResultType(0);

    // Create a new NumberLookup by calling the createNumberLookup method and providing the
    // params instance as parameters
    return numberLookupController.createNumberlookup(params);
  }

  /**
   * This example shows how to create a NumberLookup by providing only a phone number as parameter
   *
   * @param phoneNumber phone number to lookup
   * @return NumberLookup instance
   * @throws TwizoException when something goes wrong during the process
   */
  NumberLookup createSimpleNumberLookup(String phoneNumber) throws TwizoException {

    // Create a new NumberLookup with a phone number as parameter
    return numberLookupController.createSimpleNumberLookup(phoneNumber);
  }

  /**
   * This example shows how to get the status of a NumberLookup
   *
   * @param messageId id of message to check it's status
   * @return NumberLookup instance
   * @throws TwizoException when something goes wrong during the process
   */
  NumberLookup getNumberLookup(String messageId) throws TwizoException {

    // Get the status of a NumberLookup by using it's messageId
    return numberLookupController.getNumberLookup(messageId);
  }

  /**
   * This example shows how to poll for results
   *
   * @return Array of NumberLookup instances of Results
   * @throws TwizoException when something goes wrong during the process
   */
  NumberLookup[] getResults() throws TwizoException {
    // Get the results of the NumberLookup by polling
    return numberLookupController.getResults();
  }
}
