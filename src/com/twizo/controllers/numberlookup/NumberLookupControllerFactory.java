package com.twizo.controllers.numberlookup;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class NumberLookupControllerFactory {

  /**
   * Get a new instance of a NumberLookupController based on the TwizoType
   *
   * @param node preferred API Node
   * @param apiKey API key of the user
   * @param twizoType type of Twizo functionality
   * @return NumberLookup instance
   */
  public NumberLookupController getInstance(String node, String apiKey, TwizoType twizoType) {

    switch (twizoType) {
      case NUMBERLOOKUP:
        return new ApiNumberLookupController(node, apiKey);
      default:
        return null;
    }
  }

}
