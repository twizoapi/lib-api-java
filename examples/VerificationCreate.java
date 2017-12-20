import com.twizo.controllers.verification.VerificationController;
import com.twizo.dataaccess.jsonparams.VerificationParams;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.VerificationException;

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
public class VerificationCreate extends AbstractExample {

    /**
     * This example shows how to create a new verification
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new VerificationController instance
        VerificationController verificationController = twizo.getVerificationController();
        try {
            // Get the recipient of the verification
            System.out.println("Enter a recipient");
            String recipient = input.readLine();

            // Create a new parameters object. It's also possible to use just the number as parameter.
            // In that case you can use the createSimpleVerification() method
            VerificationParams params = new VerificationParams();

            // Set recipient in VerificationParams object
            params.setRecipient(recipient);

            // Create the verification with the params object as parameter and print the result
            System.out.println(verificationController.createVerification(params));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | VerificationException e) {
            Logger.getLogger(VerificationCreate.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
