import com.twizo.controllers.application.ApplicationController;
import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerifyCredentials extends AbstractExample {

    /**
     * This example shows how to verify your API credentials
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new applicationController instance
        ApplicationController applicationController = twizo.getApplicationController();
        try {
            // Call the verifyCredentials method to verify your credentials
            System.out.println(applicationController.verifyCredentials());
        } catch (TwizoCallException | TwizoJsonParseException | ApplicationException e) {
            Logger.getLogger(VerifyCredentials.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
