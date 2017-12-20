package com.twizo.services.biovoice;

import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.BiovoiceRegistration;
import com.twizo.models.BiovoiceSubscription;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonBiovoiceService extends AbstractService implements BiovoiceService {

    /**
     * Parse received information to a BiovoiceRegistration object
     *
     * @param data which has to get parsed
     * @return BiovoiceRegistration object
     * @throws TwizoJsonParseException when something goes swrong during JSON parsing
     */
    @Override
    public BiovoiceRegistration parseBiovoiceRegistration(String data) throws TwizoJsonParseException {
        try {
            return gson.fromJson(data, BiovoiceRegistration.class);
        } catch (JsonSyntaxException ex) {
            throw new TwizoJsonParseException(ex);
        }
    }

    /**
     * Parse received information to a BiovoiceSubscription object
     *
     * @param data which has to get parsed
     * @return BiovoiceSubscription object
     * @throws TwizoJsonParseException when something goes swrong during JSON parsing
     */
    @Override
    public BiovoiceSubscription parseBiovoiceSubscription(String data) throws TwizoJsonParseException {
        try {
            return gson.fromJson(data, BiovoiceSubscription.class);
        } catch (JsonSyntaxException ex) {
            throw new TwizoJsonParseException(ex);
        }
    }
}
