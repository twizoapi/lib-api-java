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
public class BiovoiceGetRegistration extends AbstractExample {

    /**
     * This example shows how get the status of Biovoice registration
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new BiovoiceController instance
        BiovoiceController biovoiceController = twizo.getBiovoiceController();
        try {
            // Read the Biovoice identifier
            System.out.println("Enter a registrationId of a biovoice registration");
            String registrationId = input.readLine();

            // Call the getBiovoiceRegistration to get the Biovoice registration
            System.out.println(biovoiceController.getBiovoiceRegistration(registrationId));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | BiovoiceException e) {
            Logger.getLogger(BiovoiceGetRegistration.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
