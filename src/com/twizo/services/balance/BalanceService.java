package com.twizo.services.balance;

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
public interface BalanceService {

  /**
   * Parse received information to a Balance object
   *
   * @param data data which has to be parsed
   * @return Balance object
   * @throws TwizoException when something goes wrong during the process
   */
  Balance parseCreditBalance(String data) throws TwizoException;
}
