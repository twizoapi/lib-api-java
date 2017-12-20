package com.twizo.services.biovoice;

import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.BiovoiceRegistration;
import com.twizo.models.BiovoiceSubscription;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface BiovoiceService {

    /**
     * Parse received information to a BiovoiceRegistration object
     *
     * @param data which has to get parsed
     * @return BiovoiceRegistration object
     * @throws TwizoJsonParseException when something goes swrong during JSON parsing
     */
    BiovoiceRegistration parseBiovoiceRegistration(String data) throws TwizoJsonParseException;

    /**
     * Parse received information to a BiovoiceSubscription object
     *
     * @param data which has to get parsed
     * @return BiovoiceSubscription object
     * @throws TwizoJsonParseException when something goes swrong during JSON parsing
     */
    BiovoiceSubscription parseBiovoiceSubscription(String data) throws TwizoJsonParseException;
}
