package com.twizo.controllers.registrationwidget;

import com.twizo.dataaccess.jsonparams.RegistrationWidgetParams;
import com.twizo.exceptions.RegistrationWidgetException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.RegistrationWidget;

public interface RegistrationWidgetController {

    /**
     * Start a registration session and parse the result to a Verification object
     *
     * @param params parameters which can be added to the request
     * @throws TwizoCallException           when something goes wrong during calling the API
     * @throws TwizoJsonParseException      when something goes wrong during JSON parsing
     * @throws RegistrationWidgetException  when something goes wrong during the process
     */
    RegistrationWidget createRegistration(RegistrationWidgetParams params) throws TwizoCallException, TwizoJsonParseException,
            RegistrationWidgetException;

}
