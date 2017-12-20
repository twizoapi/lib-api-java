package com.twizo.services.registrationwidget;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.RegistrationWidgetException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.RegistrationWidget;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonRegistrationWidgetService extends AbstractService implements
        RegistrationWidgetService {

    /**
     * Parse RegistrationWidget data received from the server to a RegistrationWidget object
     *
     * @param data data to parse
     * @return RegistrationWidget instance
     * @throws RegistrationWidgetException when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public RegistrationWidget parseRegistrationWidget(String data) throws RegistrationWidgetException, TwizoJsonParseException {
        if (data != null) {
            try {
                JsonObject jsonObject = new JsonObject();
                RegistrationWidget registrationWidget = gson.fromJson(data, RegistrationWidget.class);
                return registrationWidget;
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }

        throw new RegistrationWidgetException("Twizo didn't respond as expected, please try again");
    }
}
