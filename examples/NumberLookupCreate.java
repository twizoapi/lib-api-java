import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.dataaccess.jsonparams.NumberLookupParams;
import com.twizo.exceptions.NumberLookupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.io.IOException;
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
public class NumberLookupCreate extends AbstractExample {

    /**
     * This example shows how to create a new NumberLookup
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new NumberLookupController instance
        NumberLookupController numberLookupController = twizo.getNumberLookupController();
        try {
            // Read the phone number to lookup
            System.out.println("Enter a phone number to lookup");
            String phoneNumber = input.readLine();

            // Create a new NumberLookupParams object
            NumberLookupParams params = new NumberLookupParams();
            params.setNumbers(new String[]{phoneNumber});

            // Create a new NumberLookup by calling the createNumberLookup method and providing the params instance
            // as parameters
            System.out.println(Arrays.asList(numberLookupController.createNumberlookup(params)));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | NumberLookupException e) {
            Logger.getLogger(NumberLookupCreate.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
