import com.twizo.controllers.biovoice.BiovoiceController;
import com.twizo.exceptions.BiovoiceException;
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
public class BiovoiceGetSubscription extends AbstractExample {

    /**
     * This example shows how get a Biovoice subscription
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new BiovoiceController instance
        BiovoiceController biovoiceController = twizo.getBiovoiceController();
        try {
            // Read the Biovoice subscription recipient
            System.out.println("Enter the phone number of a biovoice subscription");
            String recipient = input.readLine();

            // Call the getBiovoiceSubscription to get the Biovoice subscription
            System.out.println(biovoiceController.getBiovoiceSubscription(recipient));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | BiovoiceException e) {
            Logger.getLogger(BiovoiceGetSubscription.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
