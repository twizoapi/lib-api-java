package com.twizo.controllers.balance;

import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Balance;
import com.twizo.services.balance.BalanceServiceFactory;
import com.twizo.services.balance.BalanceService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class ApiBalanceController extends TwizoController implements
    BalanceController {

  /**
   * BalanceController instance to parse server results
   */
  private final BalanceService balanceService;

  /**
   * Create a new ApiBalanceController instance
   *
   * @param apiHost Preferred API Node
   * @param apiKey Accesskey to the API
   */
  ApiBalanceController(String apiHost, String apiKey) {
    super(apiHost, apiKey);

    BalanceServiceFactory balanceServiceFactory = new BalanceServiceFactory();
    balanceService = balanceServiceFactory.getInstance();
  }

  /**
   * Get Balance of the user and parse it to a Balance object
   *
   * @return Balance object with information about the balance inside
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Balance getCreditBalance() throws TwizoException {
    return balanceService
        .parseCreditBalance(worker.execute("wallet/getbalance", null, RequestType.GET));
  }
}
