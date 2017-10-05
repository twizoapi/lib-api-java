package com.twizo.controllers.balance;

import com.twizo.controllers.TestSetup;
import com.twizo.controllers.balance.ApiBalanceController;
import com.twizo.controllers.balance.BalanceController;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Balance;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BalanceTest extends TestSetup {

  private BalanceController balanceController;

  @Before
  public void setUp() {
    balanceController = new ApiBalanceController(NODE, TESTAPIKEY);
  }

  /**
   * This method tests the parseCreditBalance method of the balanceController
   */
  @Test
  public void getCreditBalanceTest() {
    Balance balance = null;
    try {
      balance = balanceController.getCreditBalance();
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }

    // Check if values are filled in
    assertNotNull(balance.getCurrencyCode());
    assertNotNull(balance.getWallet());
  }

}