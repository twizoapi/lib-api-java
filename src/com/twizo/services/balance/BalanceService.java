package com.twizo.services.balance;

import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.BalanceException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Balance;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface BalanceService {

    /**
     * Parse received information to a Balance object
     *
     * @param data data which has to be parsed
     * @return Balance object
     * @throws BalanceException        when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    Balance parseCreditBalance(String data) throws TwizoJsonParseException, BalanceException;
}
