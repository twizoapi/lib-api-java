package com.twizo.controllers.application;

import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Credentials;
import com.twizo.services.application.ApplicationService;
import com.twizo.services.application.JsonApplicationService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiApplicationController extends TwizoController implements ApplicationController {

    private final ApplicationService applicationService;

    /**
     * Create a new ApiApplicationController instance
     *
     * @param worker Api worker
     */
    public ApiApplicationController(Worker worker) {
        super(worker);
        applicationService = new JsonApplicationService();
    }

    /**
     * Get details about the entered API key
     *
     * @return Credentials object containing information about the key
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws ApplicationException    when credentials are invalid
     */
    @Override
    public Credentials verifyCredentials() throws TwizoCallException, TwizoJsonParseException, ApplicationException {
        try {
            return applicationService
                    .parseCredentials(worker.execute("application/verifycredentials", null, RequestType.GET));
        } catch (TwizoCallException e) {
            throw new ApplicationException("The entered credentials are invalid");
        }
    }

    /**
     * Get allowed types for a specific API key
     *
     * @return JsonElement of allowed types
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws ApplicationException    when something goes wrong during the process
     */
    @Override
    public String[] getAllowedTypes() throws TwizoCallException, TwizoJsonParseException, ApplicationException {
        return applicationService.parseAllowedTypes(worker.execute("application/verification_types",
                null, RequestType.GET));
    }

}
