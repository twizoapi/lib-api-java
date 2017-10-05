import com.twizo.Twizo;
import com.twizo.controllers.balance.BalanceController;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Balance;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class BalanceExample {

  private final Twizo twizo;

  BalanceExample(Twizo twizo) {
    this.twizo = twizo;
  }

  /**
   * This example shows how to use the getCreditBalance method of the BalanceController class
   *
   * @return CreditBalance instance
   * @throws TwizoException when something goes wrong during the process
   */
  Balance getCreditBalanceExample() throws TwizoException {

    // Get a new BalanceController instance
    BalanceController balanceController = twizo.getBalanceController();

    // Getting the balance object by calling getCreditBalance() on the controller
    return balanceController.getCreditBalance();
  }
}
