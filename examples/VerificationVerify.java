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
public class VerificationVerify extends AbstractExample {

    /**
     * This example shows how to verify a received token
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
            System.out.println("Enter the messageId of the verification");
            String messageId = input.readLine();

            // Get the recipient of the verification
            System.out.println("Enter the verification token");
            String token = input.readLine();

            // Verify the token with the token and messageId as parameters and print the result
            System.out.println(verificationController.verifyToken(messageId, token));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | VerificationException e) {
            Logger.getLogger(VerificationVerify.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
