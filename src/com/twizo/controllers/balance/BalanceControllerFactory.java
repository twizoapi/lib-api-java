package com.twizo.controllers.balance;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BalanceControllerFactory {

  /**
   * Get a new instance of BalanceController based on the TwizoType
   *
   * @param apiHost preferred API Node
   * @param apiKey API key of the user
   * @param twizoType type of Twizo functionality
   * @return AccountControllerFactory instance
   */
  public BalanceController getInstance(String apiHost, String apiKey, TwizoType twizoType) {
    switch (twizoType) {
      case ACCOUNT:
        return new ApiBalanceController(apiHost, apiKey);
      default:
        return null;
    }
  }

}
