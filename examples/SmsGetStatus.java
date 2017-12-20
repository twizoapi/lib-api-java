import com.twizo.controllers.sms.SmsController;
import com.twizo.exceptions.SmsException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsGetStatus extends AbstractExample {

    /**
     * This example shows how to get the status of a created Sms message
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new SmsController instance
        SmsController smsController = twizo.getSmsController();
        try {
            // Get the messageId of a sms
            System.out.println("Enter a sms messageId");
            String messageId = input.readLine();

            // Send a new SimpleSms by giving just the recipient, body and sender
            System.out.println(smsController.getStatus(messageId));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | SmsException e) {
            Logger.getLogger(SmsGetStatus.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
