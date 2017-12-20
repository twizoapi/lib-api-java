package com.twizo.services.application;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Balance;

import com.twizo.models.Credentials;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonApplicationService extends AbstractService implements
        ApplicationService {

    private JsonParser jsonParser;

    public JsonApplicationService() {
        this.jsonParser = new JsonParser();
    }

    /**
     * Parse received information to a Credentials object
     *
     * @param data data which has to be parsed
     * @return Credentials object
     * @throws ApplicationException    when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public Credentials parseCredentials(String data) throws TwizoJsonParseException, ApplicationException {
        if (data != null) {
            try {
                return gson.fromJson(data, Credentials.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new ApplicationException("Twizo didn't response as expected, please try again");
    }

    /**
     * Parse received information to a String array of allowed types *
     *
     * @param data data which has to be parsed
     * @return String array object
     * @throws ApplicationException    when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public String[] parseAllowedTypes(String data) throws TwizoJsonParseException, ApplicationException {
        if (data != null) {
            try {
                JsonObject object = jsonParser.parse(data).getAsJsonObject();
                String[] allowedTypes = new String[object.size()];

                for (int i = 0; i < object.size(); i++) {
                    allowedTypes[i] = object.get(String.valueOf(i)).toString().replace("\"", "");
                }

                return allowedTypes;
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new ApplicationException("Twizo didn't respond as expected, please try again");
    }
}
