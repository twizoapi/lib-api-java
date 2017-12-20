import com.twizo.controllers.widget.VerificationWidgetController;
import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
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
public class WidgetVerificationCreateJson extends AbstractExample {

    /**
     * This example shows how to create a new widgetSession and get the result in Json format
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

            // Create a new WidgetSessionParams object
            WidgetSessionParams params = new WidgetSessionParams();
            params.setAllowedTypes(new String[]{"sms", "call"});
            params.setRecipient(recipient);

            // Create a new WidgetSession with WidgetSessionParams as parameters and return the result as
            // an Json String
            System.out.println(verificationController.createWidgetSessionJson(params));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | WidgetException e) {
            Logger.getLogger(WidgetVerificationCreateJson.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
