package com.twizo.controllers.verification;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationControllerFactory {

  /**
   * Get a new instance of a VerificationController based on twizoType
   *
   * @param node preferred API Node
   * @param apiKey API key of the user
   * @param twizoType type of Twizo functionality
   * @return VerificationController instance
   */
  public VerificationController getInstance(String node, String apiKey, TwizoType twizoType) {
    switch (twizoType) {
      case VERIFICATION:
        return new ApiVerificationController(node, apiKey);
      default:
        return null;
    }
  }

}
