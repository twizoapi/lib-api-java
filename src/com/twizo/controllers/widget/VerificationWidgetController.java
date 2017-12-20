package com.twizo.controllers.widget;

import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoCallException;
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
public interface VerificationWidgetController {

    /**
     * Create a new widgetSession and parse the result to a WidgetSession object
     *
     * @param params WidgetSessionParams object to add parameters to request
     * @return WidgetSession object
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    WidgetSession createWidgetSession(WidgetSessionParams params) throws TwizoCallException, TwizoJsonParseException,
            WidgetException;

    /**
     * Create a new widgetSession and send the result back as a Json String
     *
     * @param params WidgetSessionParams object to add parameters to request
     * @return Json String
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    String createWidgetSessionJson(WidgetSessionParams params) throws TwizoCallException, TwizoJsonParseException,
            WidgetException;

    /**
     * Get the status of a session and parse the result to a WidgetSession object
     *
     * @param sessionToken identifier of the session
     * @param recipient    phone number specified for the session
     * @param identifier   backup code identifier
     * @param type         type of session creation
     * @return WidgetSession object
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    WidgetSession getSessionStatus(String sessionToken, String recipient, String identifier, WidgetSessionType type)
            throws TwizoCallException, TwizoJsonParseException, WidgetException;

    /**
     * Get the status of a session and send the result back as a Json String
     *
     * @param sessionToken identifier of the session
     * @param recipient    phone number specified for the session
     * @param identifier   backup code identifier
     * @param type         type of session creation
     * @return Json String
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws WidgetException         when something goes wrong during the process
     */
    String getSessionStatusJson(String sessionToken, String recipient, String identifier, WidgetSessionType type)
            throws TwizoCallException, TwizoJsonParseException, WidgetException;

}
