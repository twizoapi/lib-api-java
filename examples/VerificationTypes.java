import com.twizo.controllers.application.ApplicationController;
import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

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
public class VerificationTypes extends AbstractExample {

    /**
     * This example shows how to get the allowed verification types for your account
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new applicationController instance
        ApplicationController applicationController = twizo.getApplicationController();
        try {
            // Call the getAllowedTypes method to get the allowed verification types for your account
            System.out.println(Arrays.asList(applicationController.getAllowedTypes()));
        } catch (TwizoCallException | TwizoJsonParseException | ApplicationException e) {
            Logger.getLogger(VerificationTypes.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
