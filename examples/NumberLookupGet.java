import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.exceptions.NumberLookupException;
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
public class NumberLookupGet extends AbstractExample {

    /**
     * This example shows how to get the status of a NumberLookup
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new NumberLookupController instance
        NumberLookupController numberLookupController = twizo.getNumberLookupController();
        try {
            // Get the messageId of a number lookup
            System.out.println("Enter a messageId of a number lookup");
            String messageId = input.readLine();

            // Create a new NumberLookup by calling the createNumberLookup method and providing the params instance
            // as parameters
            System.out.println(numberLookupController.getNumberLookup(messageId));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | NumberLookupException e) {
            Logger.getLogger(NumberLookupGet.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
