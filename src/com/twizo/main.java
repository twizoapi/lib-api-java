package com.twizo;

import com.twizo.dataaccess.Node;
import com.twizo.dataaccess.jsonparams.BiovoiceParams;
import com.twizo.exceptions.ApplicationException;
import com.twizo.exceptions.BiovoiceException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.util.Arrays;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class main {

    /**
     * This example shows how to verify your API credentials
     *
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws ApplicationException    when credentials are invalid
     */
    public static void main(String[] args) throws TwizoCallException, ApplicationException, TwizoJsonParseException {
        Twizo twizo = new TwizoFactory("JyU5_Ca3Q2euxBD2hZz9BCLhif38iLAkiwRJ-LIyF5UU59mA", Node.ASIA);

        BiovoiceParams params = new BiovoiceParams();
        params.setRecipient("60123000008");
        try {
            System.out.println(Arrays.asList(twizo.getBiovoiceController().createBiovoiceRegistration(params)));
        } catch (BiovoiceException e) {
            e.printStackTrace();
        }
    }

}
