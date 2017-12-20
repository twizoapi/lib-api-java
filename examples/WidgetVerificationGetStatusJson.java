import com.twizo.controllers.widget.VerificationWidgetController;
import com.twizo.controllers.widget.WidgetSessionType;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.WidgetException;

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
public class WidgetVerificationGetStatusJson extends AbstractExample {

    /**
     * This example shows how to get the status of a session and get the status of a session in Json
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new VerificationWidgetController instance
        VerificationWidgetController verificationController = twizo.getVerificationWidgetController();
        try {
            // Get the recipient of the verification
            System.out.println("Enter the recipient of the verification");
            String recipient = input.readLine();

            // Get the sessionToken
            System.out.println("Enter the sessionToken");
            String sessionToken = input.readLine();

            // Get the sessionStatus and print the result in Json
            System.out.println(verificationController.getSessionStatusJson(sessionToken, recipient, null,
                    WidgetSessionType.RECIPIENT));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | WidgetException e) {
            Logger.getLogger(WidgetVerificationGetStatusJson.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
