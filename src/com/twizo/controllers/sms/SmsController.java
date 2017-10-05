package com.twizo.controllers.sms;

import com.twizo.dataaccess.jsonparams.SmsParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Sms;
import com.twizo.models.SmsType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface SmsController {

  /**
   * Create and send a new Sms and parse the result to a Sms object
   *
   * @param params Parameters which will be added to the request
   * @param type type of Sms (simple/advanced)
   * @return Result of Sms creation
   * @throws TwizoException when something goes wrong during the process
   */
  Sms[] send(SmsParams params, SmsType type) throws TwizoException;

  /**
   * Send a simple sms
   *
   * @param recipient recipient of the sms
   * @param body body of the sms
   * @param sender sender of the sms
   * @return Result of sms creation
   * @throws TwizoException when something goes wrong during the process
   */
  Sms sendSimple(String recipient, String body, String sender) throws TwizoException;

  /**
   * Get the status of a Sms and parse it to a Sms object
   *
   * @param messageId Id of Sms
   * @return Result of GET request to get information about a specific sms
   * @throws TwizoException when something goes wrong during the process
   */
  Sms getStatus(String messageId) throws TwizoException;

  /**
   * Poll results about Sms delivery reports.
   *
   * @return Array of Sms objects
   * @throws TwizoException when something goes wrong during the process
   */
  Sms[] getDeliveryReports() throws TwizoException;
}
