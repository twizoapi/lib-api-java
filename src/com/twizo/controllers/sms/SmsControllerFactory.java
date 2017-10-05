package com.twizo.controllers.sms;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsControllerFactory {

  /**
   * Get a new instance of SmsController based on the TwizoType
   *
   * @param node preferred API Node
   * @param apiKey API key of the user
   * @param twizoType chosen Twizo functionality
   * @return ISmsController instance
   */
  public SmsController getInstance(String node, String apiKey, TwizoType twizoType) {
    switch (twizoType) {
      case SMS:
        return new ApiSmsController(node, apiKey);
      default:
        return null;
    }
  }
}
