package com.twizo.services.verification;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationServiceFactory {

  public VerificationService getInstance(TwizoType twizoType) {
    switch (twizoType) {
      case VERIFICATION:
        return new JsonVerificationService();
      default:
        return null;
    }
  }

}
