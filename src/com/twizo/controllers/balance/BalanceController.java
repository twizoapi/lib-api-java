package com.twizo.controllers.balance;

import com.twizo.exceptions.BalanceException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Balance;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface BalanceController {

    /**
     * Get Balance of the user and parse it to a Balance object
     *
     * @return Balance object with information about the application inside
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BalanceException        when something goes wrong during the process
     */
    Balance getCreditBalance() throws TwizoCallException, TwizoJsonParseException, BalanceException;

}
