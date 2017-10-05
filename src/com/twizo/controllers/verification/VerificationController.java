package com.twizo.controllers.verification;

import com.twizo.dataaccess.jsonparams.VerificationParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Verification;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface VerificationController {

  /**
   * Create and send a new verification and parse the result to a Verification object
   *
   * @param params parameters which can be added to the request
   * @return Result of Verification creation
   * @throws TwizoException when something goes wrong during the process
   */
  Verification createVerification(VerificationParams params) throws TwizoException;

  /**
   * Create and send a new verification by providing just a phone number
   *
   * @param phoneNumber phone number to create verification for
   * @return Result of Verification creation
   * @throws TwizoException when something goes wrong during the process
   */
  Verification createSimpleVerification(String phoneNumber) throws TwizoException;

  /**
   * Verify a received token and parse the result to a Verification object
   *
   * @param messageId id of the received token message
   * @param token token which has to get verified
   * @return Result of Verification
   * @throws TwizoException when something goes wrong during the process
   */
  Verification verifyToken(String messageId, String token) throws TwizoException;

  /**
   * Get the status of a verification
   *
   * @param messageId id of the verification which will be looked up
   * @return result of the status lookup
   * @throws TwizoException when something goes wrong during the process
   */
  Verification getVerificationStatus(String messageId) throws TwizoException;

}
