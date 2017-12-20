package com.twizo.controllers.totp;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.TotpParams;
import com.twizo.exceptions.TotpException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Totp;
import com.twizo.services.totp.JsonTotpService;
import com.twizo.services.totp.TotpService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiTotpController extends TwizoController implements TotpController {

    private final TotpService totpService;

    /**
     * Create a new ApiTotpController instance
     *
     * @param worker Api worker
     */
    public ApiTotpController(Worker worker) {
        super(worker);

        totpService = new JsonTotpService();
    }

    /**
     * Create a new Totp and parse the result to a Totp object
     *
     * @param params TotpParams object to add parameters to the request
     * @return Totp instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public Totp createTotp(TotpParams params) throws TwizoCallException, TwizoJsonParseException, TotpException {
        return totpService.parseTotp(worker.execute("totp", processParams(params), RequestType.POST));
    }

    /**
     * Verify a Totp token
     *
     * @param identifier unique identifier of a Totp session
     * @param token      verification token
     * @return Totp token
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public Totp verifyTotp(String identifier, String token) throws TwizoCallException, TwizoJsonParseException,
            TotpException {
        return totpService.parseVerification(worker.execute(String.format("totp/%s?token=%s", identifier, token),
                null, RequestType.GET));
    }

    /**
     * Get the status of a Totp and parse the result to a Totp object
     *
     * @param identifier unique id of Totp
     * @return Totp instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public Totp getTotpStatus(String identifier) throws TwizoCallException, TwizoJsonParseException, TotpException {
        return totpService.parseTotp(worker.execute(String.format("totp/%s", identifier), null, RequestType.GET));
    }

    /**
     * Delete a Totp instance from Twizo's servers
     *
     * @param identifier unique id of Totp
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public void deleteTotp(String identifier) throws TwizoCallException, TwizoJsonParseException, TotpException {
        worker.execute(String.format("totp/%s", identifier), null, RequestType.DELETE);
    }

    /**
     * This method parses parameters to JSON
     *
     * @param params parameters which will be added to the request
     * @return parameters in JSON format
     * @throws TwizoJsonParseException when something goes wrong when parsing JSON
     */
    private String processParams(TotpParams params) throws TwizoJsonParseException {
        try {
            return gson.toJson(params);
        } catch (JsonSyntaxException ex) {
            throw new TwizoJsonParseException(ex);
        }
    }
}
