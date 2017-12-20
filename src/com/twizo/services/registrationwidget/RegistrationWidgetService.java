package com.twizo.services.registrationwidget;

import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.RegistrationWidgetException;
import com.twizo.models.RegistrationWidget;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface RegistrationWidgetService {

    /**
     * Parse RegistrationWidget data received from the server to a RegistrationWidget object
     *
     * @param data data to parse
     * @return RegistrationWidget instance
     * @throws RegistrationWidgetException when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    RegistrationWidget parseRegistrationWidget(String data) throws RegistrationWidgetException, TwizoJsonParseException;
}
