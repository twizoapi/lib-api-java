package com.twizo.services.widget;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.WidgetException;
import com.twizo.models.Verification;
import com.twizo.models.WidgetSession;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonVerificationWidgetService extends AbstractService implements
        VerificationWidgetService {

    /**
     * Parse widgetSession data received from the server to a WidgetSession object
     *
     * @param data data to parse
     * @return WidgetSession instance
     * @throws WidgetException when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public WidgetSession parseWidgetSession(String data) throws WidgetException, TwizoJsonParseException {
        if (data != null) {
            try {
                JsonObject jsonObject = new JsonObject();
                WidgetSession widgetSession = gson.fromJson(data, WidgetSession.class);
                widgetSession.setVerification(gson.fromJson(jsonObject.getAsJsonObject("verification"),
                        Verification.class));
                return gson.fromJson(data, WidgetSession.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new WidgetException("Twizo didn't response as expected, please try again");
    }
}
