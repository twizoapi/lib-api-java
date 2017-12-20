import com.twizo.controllers.totp.TotpController;
import com.twizo.dataaccess.jsonparams.TotpParams;
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
public class TotpCreate extends AbstractExample {

    /**
     * This example shows how to create a new Totp verification
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

            // Read the Totp issuer
            System.out.println("Enter a issuer for Totp");
            String issuer = input.readLine();

            // Create a new TotpParams instance for adding parameters to the request
            TotpParams params = new TotpParams();

            // Set the mandatory identifier parameter
            params.setIdentifier(identifier);

            // Set the mandatory issuer parameter
            params.setIssuer(issuer);

            // Call the createTotp method to create a new Totp verification.
            System.out.println(totpController.createTotp(params));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | TotpException e) {
            Logger.getLogger(TotpCreate.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
