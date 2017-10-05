package com.twizo.services.balance;

import com.twizo.exceptions.TwizoException;
import com.twizo.models.Balance;

import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class JsonBalanceService extends AbstractService implements
    BalanceService {

  /**
   * Parse the received information to a Balance object
   *
   * @param data data which has to be parsed
   * @return Balance object with data from data source inside
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Balance parseCreditBalance(String data) throws TwizoException {
    if (data != null) {
      return gson.fromJson(data, Balance.class);
    } else {
      throw new TwizoException("Twizo didn't response as expected, please try again");
    }
  }
}
