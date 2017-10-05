import com.twizo.Twizo;
import com.twizo.controllers.verification.VerificationController;
import com.twizo.dataaccess.jsonparams.VerificationParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Verification;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class VerificationExample {

  private final VerificationController verificationController;

  VerificationExample(Twizo twizo) {

    // Get a new VerificationController instance
    verificationController = twizo.getVerificationController();
  }

  /**
   * This example shows how to create a new verification
   *
   * @param recipient recipient of the verification
   * @return Verification object
   * @throws TwizoException when something goes wrong during the process
   */
  Verification createVerification(String recipient) throws TwizoException {

    // Create a new parameters object. It's also possible to use just the number as parameter.
    // In that case you can use the createSimpleVerification() method
    VerificationParams params = new VerificationParams();

    // Set recipient in VerificationParams object
    params.setRecipient(recipient);

    // Create the verification with the params object as parameter and return the result
    return verificationController.createVerification(params);
  }

  /**
   * This example shows how to verify a received token
   *
   * @param messageId id of received verification message
   * @param token token to verify
   * @return Verification instance
   * @throws TwizoException when something goes wrong during the process
   */
  Verification verifyToken(String messageId, String token) throws TwizoException {

    // Verify the token with the token and messageId as parameters and return the result
    return verificationController.verifyToken(messageId, token);
  }

  /**
   * This example shows how to get the status of a created verification
   *
   * @param messageId id of received verification message
   * @return Verification instance
   * @throws TwizoException when something goes wrong during the process
   */
  Verification getVerificationStatus(String messageId) throws TwizoException {

    // Get the status of a verification by using it's messageId
    return verificationController.getVerificationStatus(messageId);
  }
}
