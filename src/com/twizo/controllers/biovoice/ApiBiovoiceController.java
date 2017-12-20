package com.twizo.controllers.biovoice;

import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.BiovoiceParams;
import com.twizo.exceptions.BiovoiceException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.BiovoiceRegistration;
import com.twizo.models.BiovoiceSubscription;
import com.twizo.services.biovoice.BiovoiceService;
import com.twizo.services.biovoice.JsonBiovoiceService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiBiovoiceController extends TwizoController implements BiovoiceController {

    private final BiovoiceService biovoiceService;

    /**
     * Create a new ApiBiovoiceController instance
     *
     * @param worker Api worker
     */
    public ApiBiovoiceController(Worker worker) {
        super(worker);

        biovoiceService = new JsonBiovoiceService();
    }

    /**
     * Create a new Biovoice registration
     *
     * @param params BiovoiceParams to add parameters to the request
     * @return BiovoiceRegistration instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    @Override
    public BiovoiceRegistration createBiovoiceRegistration(BiovoiceParams params) throws TwizoCallException,
            TwizoJsonParseException, BiovoiceException {
        return biovoiceService.parseBiovoiceRegistration(worker.execute("biovoice/registration",
                processParams(params), RequestType.POST));
    }

    /**
     * Get the status of a Biovoice registration
     *
     * @param registrationId unique identifier of the Biovoice registration
     * @return BiovoiceRegistration instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    @Override
    public BiovoiceRegistration getBiovoiceRegistration(String registrationId) throws TwizoCallException,
            TwizoJsonParseException, BiovoiceException {
        return biovoiceService.parseBiovoiceRegistration(worker.execute(String.format("biovoice/registration/%s",
                registrationId), null, RequestType.GET));
    }

    /**
     * Get the status of a Biovoice subscription
     *
     * @param recipient unique identifier of the Biovoice subscription
     * @return BiovoiceSubscription instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    @Override
    public BiovoiceSubscription getBiovoiceSubscription(String recipient) throws TwizoCallException,
            TwizoJsonParseException, BiovoiceException {
        return biovoiceService.parseBiovoiceSubscription(worker.execute(String.format("biovoice/subscription/%s",
                recipient), null, RequestType.GET));
    }

    /**
     * Delete a Biovoice subscription
     *
     * @param recipient unique identifier of a Biovoice subscription
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BiovoiceException       when something goes wrong during the process
     */
    @Override
    public void deleteBiovoiceSubscription(String recipient) throws TwizoCallException, TwizoJsonParseException,
            BiovoiceException {
        worker.execute(String.format("biovoice/subscription/%s", recipient), null, RequestType.DELETE);
    }

    /**
     * This method parses parameters to JSON
     *
     * @param params parameters which will be added to the request
     * @return parameters in JSON format
     * @throws TwizoJsonParseException when something goes wrong when parsing JSON
     */
    private String processParams(BiovoiceParams params) throws TwizoJsonParseException {
        try {
            return gson.toJson(params);
        } catch (Exception ex) {
            throw new TwizoJsonParseException(ex);
        }
    }
}
