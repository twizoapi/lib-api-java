import com.twizo.Twizo;
import com.twizo.controllers.sms.SmsController;
import com.twizo.dataaccess.jsonparams.SmsParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Sms;
import com.twizo.models.SmsType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class SmsExample {

  private final SmsController smsController;

  SmsExample(Twizo twizo) {

    // Get a new SmsController instance
    smsController = twizo.getSmsController();
  }

  /**
   * This example shows how to sendSimple a sms message by using the SmsParams object as parameter
   *
   * @param recipient recipient of the sms
   * @return Array of sms instances
   * @throws TwizoException when something goes wrong during the process
   */
  Sms[] sendSms(String recipient) throws TwizoException {
    // Create a SmsParams instance to pass parameters to the request
    SmsParams smsParams = new SmsParams();

    // Add the recipients to the request
    smsParams.setRecipients(new String[]{recipient});

    // Add a message body to the request
    smsParams.setBody("Hello World");

    // Add a sender to the request
    smsParams.setSender("60123456789");

    // Send a new sms by calling the sendSimple method and providing the created SmsParams en SmsType
    // as parameters
    return smsController.send(smsParams, SmsType.SIMPLE);
  }

  /**
   * This example shows how to sendSimple a simple sms by providing only the recipient, body and
   * sender of the sms
   *
   * @param recipient recipient of the sms
   * @return Sms instance
   * @throws TwizoException when something goes wrong during the process
   */
  Sms sendSimpleSms(String recipient) throws TwizoException {
    // Send a new SimpleSms by giving just the recipient, body and sender
    return smsController.sendSimple(recipient, "Hello World", "60123456789");
  }

  /**
   * This example shows how to get the status of a created Sms message
   *
   * @param messageId id of the message to lookup
   * @return Sms instance of result
   * @throws TwizoException when something goes wrong during the process
   */
  Sms getStatus(String messageId) throws TwizoException {
    // Get the current status of a sms by using it's messageId
    return smsController.getStatus(messageId);
  }

  /**
   * This example shows how to poll for Delivery reports
   *
   * @return Array of Sms instances
   * @throws TwizoException when something goes wrong during the process
   */
  Sms[] getDeliveryReports() throws TwizoException {
    // Get the delivery reports by calling the getDeliveryReports method which start polling
    // This will only return when there are sms messages created with resultType 2
    return smsController.getDeliveryReports();
  }
}