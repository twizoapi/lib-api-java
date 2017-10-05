package com.twizo.controllers.widget;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationWidgetControllerFactory {

  /**
   * Get a new instance of a VerificationWidgetController based on twizoType
   *
   * @param node preferred API Node
   * @param apiKey API key of the user
   * @param twizoType type of Twizo functionality
   * @return VerificationWidgetController instance
   */
  public VerificationWidgetController getInstance(String node, String apiKey, TwizoType twizoType) {
    switch (twizoType) {
      case VERIFICATIONWIDGET:
        return new ApiVerificationWidgetController(node, apiKey);
      default:
        return null;
    }
  }

}
