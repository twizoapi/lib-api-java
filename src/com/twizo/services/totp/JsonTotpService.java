package com.twizo.services.totp;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.TotpException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Totp;
import com.twizo.models.Verification;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonTotpService extends AbstractService implements TotpService {

    /**
     * Parse received information to a Totp object
     *
     * @param data data which has to be parsed
     * @return Totp object
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public Totp parseTotp(String data) throws TwizoJsonParseException, TotpException {
        if (data != null) {
            try {
                return gson.fromJson(data, Totp.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new TotpException("Twizo didn't respond as expected, please try again");
    }

    /**
     * Parse received information to a Totp object
     *
     * @param data data which has to be parsed
     * @return Totp object
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws TotpException           when something goes wrong during the process
     */
    @Override
    public Totp parseVerification(String data) throws TwizoJsonParseException, TotpException {
        if (data != null) {
            JsonObject all = gson.fromJson(data, JsonObject.class);

            Totp totp = new Totp();

            if (all.has("identifier")) {
                totp.setIdentifier(all.get("identifier").getAsString());
            }

            if (all.has("issuer")) {
                totp.setIssuer(all.get("issuer").getAsString());
            }

            if (all.has("uri")) {
                totp.setUri(all.get("uri").getAsString());
            }
            totp.setVerification(gson.fromJson(all.getAsJsonObject("_embedded").getAsJsonObject("verification"),
                    Verification.class));

            return totp;
        }
        throw new TotpException("Twizo didn't respond as expected, please try again");
    }
}
