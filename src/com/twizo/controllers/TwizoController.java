package com.twizo.controllers;

import com.google.gson.Gson;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.TwizoCallable;

import com.twizo.exceptions.TwizoException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

  public TwizoController(String apiHost, String apiKey) {
    worker = new Worker(apiHost, apiKey);
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
  protected class Worker {

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

    Worker(String apiHost, String apiKey) {
      this.apiHost = apiHost;
      this.apiKey = apiKey;
      this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Execute TwizoCallable to interact with Twizo servers
     *
     * @param functionUrl parameter to specify API action
     * @param params parameters which can be added to an API request
     * @param requestType type of request (GET, POST, PUT, DELETE)
     * @return String in JSON format with received information
     * @throws TwizoException when something goes wrong during executing the Task
     */
    public String execute(String functionUrl, String params, RequestType requestType)
        throws TwizoException {
      try {
        return executorService
            .submit(new TwizoCallable(apiHost, apiKey, functionUrl, params, requestType)).get();
      } catch (InterruptedException | ExecutionException | TwizoException e) {
        throw new TwizoException(e);
      }
    }

  }
}
