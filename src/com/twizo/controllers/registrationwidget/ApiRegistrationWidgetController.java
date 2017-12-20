package com.twizo.controllers.registrationwidget;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.RegistrationWidgetParams;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.RegistrationWidgetException;
import com.twizo.models.RegistrationWidget;
import com.twizo.services.registrationwidget.JsonRegistrationWidgetService;
import com.twizo.services.registrationwidget.RegistrationWidgetService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiRegistrationWidgetController extends TwizoController implements
        RegistrationWidgetController {

    private final RegistrationWidgetService registrationWidgetService;

    public ApiRegistrationWidgetController(Worker worker) {
        super(worker);

        registrationWidgetService = new JsonRegistrationWidgetService();
    }

    /**
     * Start a registration session and parse the result to a Verification object
     *
     * @param params parameters which can be added to the request
     * @throws TwizoCallException           when something goes wrong during calling the API
     * @throws TwizoJsonParseException      when something goes wrong during JSON parsing
     * @throws RegistrationWidgetException  when something goes wrong during the process
     */
    @Override
    public RegistrationWidget createRegistration(RegistrationWidgetParams params) throws TwizoCallException, TwizoJsonParseException, RegistrationWidgetException {
        return registrationWidgetService.parseRegistrationWidget(
                worker.execute("widget-register-verification/session", processParams(params), RequestType.POST));
    }

    /**
     * Process parameters for request
     *
     * @param params params to process
     * @return parameters in json format
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws RegistrationWidgetException         when something goes wrong during the process
     */

    private String processParams(RegistrationWidgetParams params) throws TwizoJsonParseException, RegistrationWidgetException {
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
        throw new RegistrationWidgetException("Parameters cannot be null");
    }
}
