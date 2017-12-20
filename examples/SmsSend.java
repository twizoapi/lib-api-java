import com.twizo.controllers.sms.SmsController;
import com.twizo.dataaccess.jsonparams.SmsParams;
import com.twizo.exceptions.SmsException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.SmsType;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsSend extends AbstractExample {

    /**
     * This example shows how to sendSimple a sms message by using the SmsParams object as parameter
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new SmsController instance
        SmsController smsController = twizo.getSmsController();
        try {
            // Read the recipient of a sms
            System.out.println("Enter a recipient for sms");
            String recipient = input.readLine();

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
            System.out.println(Arrays.asList(smsController.send(smsParams, SmsType.SIMPLE)));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | SmsException e) {
            Logger.getLogger(SmsSend.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
