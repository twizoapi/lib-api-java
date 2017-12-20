package com.twizo.controllers;

import com.google.gson.Gson;
import com.twizo.dataaccess.Worker;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public abstract class TwizoController {

    /**
     * GSON instance to parse JSON strings to objects
     */
    protected final Gson gson;

    /**
     * Worker instance to communicate with Twizo's servers
     */
    protected final Worker worker;

    public TwizoController(Worker worker) {
        this.worker = worker;
        gson = new Gson();
    }

    /**
     * This method processes a phoneNumber to increase the change it's formatted correctly
     *
     * @param phoneNumber phone number which has to get processed
     * @return formatted phone number
     */
    protected String processPhoneNumber(String phoneNumber) {
        String keepNumbers = phoneNumber.replaceAll("\\s+|\\D+", "");
        return keepNumbers.replaceAll("^0+", "");
    }

    /**
     * Inner classes which is used to execute Callables to get connection to the Twizo servers
     */
}
