package com.twizo.controllers.widget;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.WidgetException;
import com.twizo.models.WidgetSession;
import com.twizo.services.widget.JsonVerificationWidgetService;
import com.twizo.services.widget.VerificationWidgetService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiVerificationWidgetController extends TwizoController implements
        VerificationWidgetController {

    private final VerificationWidgetService verificationWidgetService;

    /**
     * Create a new ApiVerificationWidgetController instance
     *
     * @param worker Api worker
     */
    public ApiVerificationWidgetController(Worker worker) {
        super(worker);

        verificationWidgetService = new JsonVerificationWidgetService();
    }

    /**
     * Create a new widgetSession and parse the result to a WidgetSession object
     *
     * @param params WidgetSessionParams object to add parameters to request
     * @return WidgetSession object
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    @Override
    public WidgetSession createWidgetSession(WidgetSessionParams params) throws TwizoCallException,
            TwizoJsonParseException, WidgetException {
        return verificationWidgetService.parseWidgetSession(
                worker.execute("widget/session", processParams(params), RequestType.POST));
    }

    /**
     * Create a new widgetSession and send the result back as a Json String
     *
     * @param params WidgetSessionParams object to add parameters to request
     * @return Json String
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    @Override
    public String createWidgetSessionJson(WidgetSessionParams params) throws TwizoCallException, TwizoJsonParseException,
            WidgetException {
        return worker.execute("widget/session", processParams(params), RequestType.POST);
    }

    /**
     * Get the status of a session and parse the result to a WidgetSession object
     *
     * @param sessionToken identifier of the session
     * @param recipient    phone number specified for the session
     * @param identifier   backup code identifier
     * @return WidgetSession object
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    @Override
    public WidgetSession getSessionStatus(String sessionToken, String recipient, String identifier,
                                          WidgetSessionType type) throws TwizoCallException, TwizoJsonParseException,
            WidgetException {

        switch (type) {
            case RECIPIENT:
                return verificationWidgetService.parseWidgetSession(
                        worker.execute(String.format("widget/session/%s?recipient=%s", sessionToken, recipient),
                                null, RequestType.GET));
            case BACKUPCODE:
                return verificationWidgetService.parseWidgetSession(
                        worker.execute(String
                                        .format("widget/session/%s?backupCodeIdentifier=%s", sessionToken, identifier),
                                null, RequestType.GET));
            case BOTH:
                return verificationWidgetService.parseWidgetSession(
                        worker.execute(String
                                        .format("widget/session/%s?recipient=%s&backupCodeIdentifier=%s", sessionToken,
                                                recipient, identifier), null,
                                RequestType.GET));
            default:
                return null;
        }
    }

    /**
     * Get the status of a session and send the result back as a Json String
     *
     * @param sessionToken identifier of the session
     * @param recipient    phone number specified for the session
     * @param identifier   backup code identifier
     * @return Json String
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    @Override
    public String getSessionStatusJson(String sessionToken, String recipient, String identifier, WidgetSessionType type)
            throws TwizoCallException, TwizoJsonParseException, WidgetException {
        switch (type) {
            case RECIPIENT:
                return worker
                        .execute(String.format("widget/session/%s?recipient=%s", sessionToken, recipient),
                                null, RequestType.GET);
            case BACKUPCODE:
                return worker.execute(String
                                .format("widget/session/%s?backupCodeIdentifier=%s", sessionToken, identifier),
                        null, RequestType.GET);
            case BOTH:
                return worker.execute(String
                                .format("widget/session/%s?recipient=%s&backupCodeIdentifier=%s", sessionToken,
                                        recipient, identifier), null,
                        RequestType.GET);
            default:
                return null;
        }
    }

    /**
     * Process parameters for request
     *
     * @param params params to process
     * @return parameters in json format
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */

    private String processParams(WidgetSessionParams params) throws TwizoJsonParseException, WidgetException {
        if (params != null) {

            // Process phone number
            if (params.getRecipient() != null) {
                params.setRecipient(processPhoneNumber(params.getRecipient()));
            }

            try {
                return gson.toJson(params);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new WidgetException("Parameters cannot be null");
    }
}
