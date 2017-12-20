import com.twizo.controllers.biovoice.BiovoiceController;
import com.twizo.dataaccess.jsonparams.BiovoiceParams;
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
public class BiovoiceCreateRegistration extends AbstractExample {

    /**
     * This example shows how to create a new Biovoice registration
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new BiovoiceController instance
        BiovoiceController biovoiceController = twizo.getBiovoiceController();
        try {
            // Read the recipient
            System.out.println("Enter a recipient for Biovoice registration");
            String recipient = input.readLine();

            // Create a new BiovoiceParams object
            BiovoiceParams params = new BiovoiceParams();
            params.setRecipient(recipient);

            // Call the createBiovoiceRegistration to create a new Biovoice registration
            System.out.println(biovoiceController.createBiovoiceRegistration(params));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | BiovoiceException e) {
            Logger.getLogger(BiovoiceCreateRegistration.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
