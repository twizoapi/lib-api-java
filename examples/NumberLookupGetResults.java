import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.exceptions.NumberLookupException;
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
public class NumberLookupGetResults extends AbstractExample {

    /**
     * This example shows how to get NumberLookup delivery reports
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new NumberLookupController instance
        NumberLookupController numberLookupController = twizo.getNumberLookupController();
        try {

            // Get the results of the NumberLookup by polling
            System.out.println(Arrays.asList(numberLookupController.getResults()));
        } catch (TwizoCallException | TwizoJsonParseException | NumberLookupException e) {
            Logger.getLogger(NumberLookupGetResults.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
