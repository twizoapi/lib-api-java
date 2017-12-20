import com.twizo.controllers.totp.TotpController;
import com.twizo.exceptions.TotpException;
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
public class TotpDelete extends AbstractExample {

    /**
     * This example shows how to delete a Totp verification
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new TotpController instance
        TotpController totpController = twizo.getTotpController();
        try {
            // Read the Totp identifier
            System.out.println("Enter a identifier for Totp");
            String identifier = input.readLine();

            // Call the deleteTotp method with the Totp identifier as parameter
            totpController.deleteTotp(identifier);
        } catch (TwizoCallException | TwizoJsonParseException | IOException | TotpException e) {
            Logger.getLogger(TotpDelete.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
