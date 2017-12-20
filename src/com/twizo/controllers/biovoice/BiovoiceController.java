package com.twizo.controllers.biovoice;

import com.twizo.dataaccess.jsonparams.BiovoiceParams;
import com.twizo.exceptions.BiovoiceException;
import com.twizo.exceptions.TwizoCallException;
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
public interface BiovoiceController {

    /**
     * Create a new Biovoice registration
     *
     * @param params BiovoiceParams to add parameters to the request
     * @return BiovoiceRegistration instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    BiovoiceRegistration createBiovoiceRegistration(BiovoiceParams params) throws TwizoCallException, TwizoJsonParseException, BiovoiceException;

    /**
     * Get the status of a Biovoice registration
     *
     * @param registrationId unique identifier of the Biovoice registration
     * @return BiovoiceRegistration instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    BiovoiceRegistration getBiovoiceRegistration(String registrationId) throws TwizoCallException, TwizoJsonParseException, BiovoiceException;

    /**
     * Get the status of a Biovoice subscription
     *
     * @param recipient unique identifier of the Biovoice subscription
     * @return BiovoiceSubscription instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    BiovoiceSubscription getBiovoiceSubscription(String recipient) throws TwizoCallException, TwizoJsonParseException, BiovoiceException;

    /**
     * Delete a Biovoice subscription
     *
     * @param recipient unique identifier of a Biovoice subscription
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    void deleteBiovoiceSubscription(String recipient) throws TwizoCallException, TwizoJsonParseException, BiovoiceException;

}
