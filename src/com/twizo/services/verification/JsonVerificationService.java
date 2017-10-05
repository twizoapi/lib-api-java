package com.twizo.services.verification;

import com.twizo.models.Verification;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class JsonVerificationService extends AbstractService implements VerificationService {

  /**
   * Parse verification data received from the server to a Verification object
   *
   * @param data data to parse
   * @return Verification instance
   */
  @Override
  public Verification parseVerification(String data) {
    return gson.fromJson(data, Verification.class);
  }
}
