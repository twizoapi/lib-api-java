import com.twizo.Twizo;
import com.twizo.controllers.widget.VerificationWidgetController;
import com.twizo.controllers.widget.WidgetSessionType;
import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.WidgetSession;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class WidgetExample {

  private final VerificationWidgetController verificationWidgetController;

  WidgetExample(Twizo twizo) {
    verificationWidgetController = twizo.getVerificationWidgetController();
  }

  /**
   * This example shows how to create a new widgetSession and get the result as an object
   *
   * @param recipient mandatory parameter if allowedType is set to sms or call
   * @param identifier mandatory parameter if allowedType is set to 'backupcode'
   * @param type defines which type of allowedType is added
   * @return WidgetSession object with session information
   * @throws TwizoException when something goes wrong during the process
   */
  WidgetSession createWidgetSession(String recipient, String identifier, WidgetSessionType type)
      throws TwizoException {

    // Create a new WidgetSessionParams object
    WidgetSessionParams params = setParameters(recipient, identifier, type);

    // Create a new WidgetSession with WidgetSessionParams as parameters and return the result as
    // an object
    return verificationWidgetController.createWidgetSession(params);
  }

  /**
   * This example shows how to create a new widgetSession and get the result as an object
   *
   * @param recipient mandatory parameter if allowedType is set to 'sms' or 'call'
   * @param identifier mandatory parameter if allowedType is set to 'backupcode'
   * @param type defines which type of allowedType is added
   * @return Json String with session information
   * @throws TwizoException when something goes wrong during the process
   */
  String createWidgetSessionJson(String recipient, String identifier, WidgetSessionType type)
      throws TwizoException {

    // Create a new WidgetSessionParams object
    WidgetSessionParams params = setParameters(recipient, identifier, type);

    // Create a new WidgetSession with WidgetSessionParams as parameters and return the result as
    // a Json String
    return verificationWidgetController.createWidgetSessionJson(params);
  }

  /**
   * This example shows how to get the status of a session and get the status of a session
   *
   * @param sessionToken unique identifier of a session
   * @param recipient mandatory parameter if allowedType is set to sms or call
   * @param identifier mandatory parameter if allowedType is set to backupcode
   * @param type defines which type of allowedType is added
   * @return WidgetSession object with session status information
   * @throws TwizoException when something goes wrong during the process
   */
  WidgetSession getSessionStatus(String sessionToken, String recipient, String identifier,
      WidgetSessionType type) throws TwizoException {

    // Get the sessionStatus and return the result as an object
    return verificationWidgetController.getSessionStatus(sessionToken, recipient, identifier, type);
  }

  /**
   * This example shows how to get the status of a session and get the status of a session
   *
   * @param sessionToken unique identifier of a session
   * @param recipient mandatory parameter if allowedType is set to sms or call
   * @param identifier mandatory parameter if allowedType is set to backupcode
   * @param type defines which type of allowedType is added
   * @return Json String with session status information
   * @throws TwizoException when something goes wrong during the process
   */
  String getSessionStatusJson(String sessionToken, String recipient, String identifier,
      WidgetSessionType type) throws TwizoException {

    // Get the sessionStatus and return the result as a Json String
    return verificationWidgetController
        .getSessionStatusJson(sessionToken, recipient, identifier, type);
  }

  /**
   * This method creates a new WidgetSessionParams object
   *
   * @param recipient parameter which is mandatory when type is set to sms or call
   * @param identifier parameter which is mandatory when type is set to backup
   * @param type defines which type of allowedType is added
   * @return new WidgetSessionParams object
   */
  private WidgetSessionParams setParameters(String recipient, String identifier,
      WidgetSessionType type) {
    // Initiate a new WidgetSessionParams object
    WidgetSessionParams params = new WidgetSessionParams();

    // Set the parameters based on the WidgetSessionType
    switch (type) {
      case RECIPIENT:
        params.setAllowedTypes(new String[]{"sms", "call"});
        params.setRecipient(recipient);
        break;
      case BACKUPCODE:
        params.setAllowedTypes(new String[]{"backupcode"});
        params.setBackupCodeIdentifier(identifier);
        break;
      case BOTH:
        params.setAllowedTypes(new String[]{"sms", "call", "backupcode"});
        params.setRecipient(recipient);
        params.setBackupCodeIdentifier(identifier);
        break;
      default:
        break;
    }

    // Return parameters object
    return params;
  }
}
