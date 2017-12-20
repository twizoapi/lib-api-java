import com.twizo.controllers.sms.SmsController;
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
public class SmsSendSimple extends AbstractExample {

    /**
     * This example shows how to sendSimple a simple sms by providing only the recipient, body and
     * sender of the sms
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

            // Send a new SimpleSms by giving just the recipient, body and sender
            System.out.println(smsController.sendSimple(recipient, "Hello World", "60123321133"));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | SmsException e) {
            Logger.getLogger(SmsSendSimple.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
