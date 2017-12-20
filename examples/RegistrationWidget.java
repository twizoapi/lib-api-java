import com.twizo.controllers.registrationwidget.RegistrationWidgetController;
import com.twizo.dataaccess.jsonparams.RegistrationWidgetParams;
import com.twizo.exceptions.RegistrationWidgetException;
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
public class RegistrationWidget extends AbstractExample {

    /**
     * This example shows how to use the createRegistration method of the RegistrationWidgetController class
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new RegistrationWidgetController instance
        RegistrationWidgetController controller = twizo.getRegistrationWidgetController();
        try {

            // Get the recipient for the registration
            System.out.println("Enter a recipient");
            String recipient = input.readLine();

            //Create a new parameters object.
            RegistrationWidgetParams params = new RegistrationWidgetParams();

            // Set recipient in RegistrationWidgetParams object
            params.setRecipient(recipient);

            // Create the registration with the params object as parameter and print the results
            System.out.println(controller.createRegistration(params));
        } catch (TwizoCallException tce) {
            System.err.println("TwizoCall exception:\n" + tce.getMessage());
        } catch (TwizoJsonParseException | RegistrationWidgetException | IOException e) {
            Logger.getLogger(RegistrationWidget.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
