package com.twizo.services.balance;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BalanceServiceFactory {

  /**
   * Get a new instance of JsonBalanceService
   *
   * @return JsonBalanceService instance
   */
  public BalanceService getInstance() {
    return new JsonBalanceService();
  }
}
