import com.twizo.controllers.balance.BalanceController;
import com.twizo.exceptions.BalanceException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class CreditBalance extends AbstractExample {

    /**
     * This example shows how to use the getCreditBalance method of the BalanceController class
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new BalanceController instance
        BalanceController backupController = twizo.getBalanceController();
        try {

            // Getting the balance object by calling getCreditBalance() on the controller
            System.out.println(backupController.getCreditBalance());
        } catch (TwizoCallException | TwizoJsonParseException | BalanceException e) {
            Logger.getLogger(CreditBalance.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
