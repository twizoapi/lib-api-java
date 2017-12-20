package com.twizo.controllers.verification;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.VerificationParams;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.VerificationException;
import com.twizo.models.Verification;
import com.twizo.services.verification.JsonVerificationService;
import com.twizo.services.verification.VerificationService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiVerificationController extends TwizoController implements VerificationController {

    private final VerificationService verificationService;

    /**
     * Create a new ApiVerificationController instance
     *
     * @param worker Api worker
     */
    public ApiVerificationController(Worker worker) {
        super(worker);

        verificationService = new JsonVerificationService();
    }

    /**
     * Create and send a new verification and parse the result to a Verification object
     *
     * @param params parameters which can be added to the request
     * @return Result of Verification creation
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws VerificationException   when something goes wrong during the process
     */
    @Override
    public Verification createVerification(VerificationParams params) throws TwizoCallException, TwizoJsonParseException,
            VerificationException {
        return verificationService
                .parseVerification(worker.execute("verification/submit", processParams(params),
                        RequestType.POST));
    }

    /**
     * Create and send a new verification by providing just a phone number
     *
     * @param phoneNumber phone number to create verification for
     * @return Result of Verification creation
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws VerificationException   when something goes wrong during the process
     */
    @Override
    public Verification createSimpleVerification(String phoneNumber) throws TwizoCallException, TwizoJsonParseException,
            VerificationException {
        VerificationParams params = new VerificationParams();
        params.setRecipient(phoneNumber);

        return verificationService.parseVerification(
                worker.execute("verification/submit", processParams(params), RequestType.POST));
    }

    /**
     * Verify a received token and parse the result to a Verification object
     *
     * @param messageId id of the received token message
     * @param token     token which has to get verified
     * @return Result of Verification
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws VerificationException   when something goes wrong during the process
     */
    @Override
    public Verification verifyToken(String messageId, String token) throws TwizoCallException, TwizoJsonParseException,
            VerificationException {
        return verificationService.parseVerification(worker
                .execute(String.format("verification/submit/%s?token=%s", messageId, token), null,
                        RequestType.GET));
    }

    /**
     * Get the status of a verification
     *
     * @param messageId id of the verification which will be looked up
     * @return result of the status lookup
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws VerificationException   when something goes wrong during the process
     */
    @Override
    public Verification getVerificationStatus(String messageId) throws TwizoCallException, TwizoJsonParseException,
            VerificationException {
        return verificationService.parseVerification(worker
                .execute(String.format("verification/submit/%s", messageId), null, RequestType.GET));
    }

    /**
     * Process parameters for request
     *
     * @param params params to process
     * @return parameters in json format
     * @throws TwizoJsonParseException when something goes wrong during the process
     */
    private String processParams(VerificationParams params) throws TwizoJsonParseException {
        if (params != null) {

            // Process phone number
            params.setRecipient(processPhoneNumber(params.getRecipient()));

            try {
                return gson.toJson(params);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        } else {
            return null;
        }
    }

}
