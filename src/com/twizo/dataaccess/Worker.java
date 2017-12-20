package com.twizo.dataaccess;

import com.twizo.exceptions.TwizoCallException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class Worker {

    /**
     * ExecutorService to execute Callables
     */
    private final ExecutorService executorService;

    /**
     * Preferred API Node
     */
    private final String apiHost;

    /**
     * Accesskey to the API
     */
    private final String apiKey;

    public Worker(String apiHost, String apiKey) {
        this.apiHost = apiHost;
        this.apiKey = apiKey;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Execute TwizoCallable to interact with Twizo servers
     *
     * @param functionUrl parameter to specify API action
     * @param params      parameters which can be added to an API request
     * @param requestType type of request (GET, POST, PUT, DELETE)
     * @return String in JSON format with received information
     * @throws TwizoCallException when something goes wrong during executing the Task
     */
    public String execute(String functionUrl, String params, RequestType requestType)
            throws TwizoCallException {
        try {
            return executorService
                    .submit(new TwizoCallable(apiHost, apiKey, functionUrl, params, requestType)).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new TwizoCallException(e);
        }
    }

}