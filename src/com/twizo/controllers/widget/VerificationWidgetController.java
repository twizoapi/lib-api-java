package com.twizo.controllers.widget;

import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoException;
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
   * @throws TwizoException when something goes wrong during the process
   */
  WidgetSession createWidgetSession(WidgetSessionParams params) throws TwizoException;

  /**
   * Create a new widgetSession and send the result back as a Json String
   *
   * @param params WidgetSessionParams object to add parameters to request
   * @return Json String
   * @throws TwizoException when something goes wrong during the process
   */
  String createWidgetSessionJson(WidgetSessionParams params) throws TwizoException;

  /**
   * Get the status of a session and parse the result to a WidgetSession object
   *
   * @param sessionToken identifier of the session
   * @param recipient phone number specified for the session
   * @param identifier backup code identifier
   * @param type type of session creation
   * @return WidgetSession object
   * @throws TwizoException when something goes wrong during the process
   */
  WidgetSession getSessionStatus(String sessionToken, String recipient, String identifier,
      WidgetSessionType type)
      throws TwizoException;

  /**
   * Get the status of a session and send the result back as a Json String
   *
   * @param sessionToken identifier of the session
   * @param recipient phone number specified for the session
   * @param identifier backup code identifier
   * @param type type of session creation
   * @return Json String
   * @throws TwizoException when something goes wrong during the process
   */
  String getSessionStatusJson(String sessionToken, String recipient, String identifier,
      WidgetSessionType type)
      throws TwizoException;

}
