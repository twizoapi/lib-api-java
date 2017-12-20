package com.twizo.services.widget;

import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.exceptions.WidgetException;
import com.twizo.models.WidgetSession;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface VerificationWidgetService {

    /**
     * Parse widgetSession data received from the server to a WidgetSession object
     *
     * @param data data to parse
     * @return WidgetSession instance
     * @throws WidgetException when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    WidgetSession parseWidgetSession(String data) throws WidgetException, TwizoJsonParseException;

}
