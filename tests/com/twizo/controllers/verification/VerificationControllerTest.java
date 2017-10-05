package com.twizo.controllers.verification;

import static org.junit.Assert.*;

import com.twizo.controllers.TestSetup;
import com.twizo.dataaccess.jsonparams.VerificationParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Verification;
import org.junit.Before;
import org.junit.Test;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationControllerTest extends TestSetup {

  private ApiVerificationController apiVerificationController;

  private static final String RECIPIENT = "6012300000";

  @Before
  public void setUp() {
    apiVerificationController = new ApiVerificationController(NODE, TESTAPIKEY);
  }

  /**
   * This method tests the creation of a Verification under normal circumstances
   */
  @Test
  public void createVerificationTest() {
    VerificationParams params = new VerificationParams();
    params.setRecipient(RECIPIENT);
    params.setTag("TestTag");

    try {
      Verification result = apiVerificationController.createVerification(params);

      // Check result
      checkVerificationResult(result);

      // Check if tag is equal to TestTag
      assertEquals("TestTag", result.getTag());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method
   */
  @Test
  public void createSimpleVerificationTest() {
    try {
      Verification result = apiVerificationController.createSimpleVerification(RECIPIENT);
      checkVerificationResult(result);
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the creation of a verification with null as input
   *
   * @throws TwizoException because input params can't be null
   */
  @Test(expected = TwizoException.class)
  public void createVerificationNullTest() throws TwizoException {
    apiVerificationController.createVerification(null);
  }

  /**
   * This method tests the getVerification method
   */
  @Test
  public void getStatusTest() {
    try {
      Verification verification = apiVerificationController.createSimpleVerification(RECIPIENT);
      Verification status = apiVerificationController
          .getVerificationStatus(verification.getMessageId());

      // Check if status is equal to 0 (no status)
      assertEquals(0, status.getStatusCode().intValue());

      // Verify token to change status
      apiVerificationController.verifyToken(verification.getMessageId(), "012345");

      status = apiVerificationController.getVerificationStatus(verification.getMessageId());

      // Check if status is equal to 1 (success)
      assertEquals(1, status.getStatusCode().intValue());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests a successful verification flow
   */
  @Test
  public void successfulVerificationTest() {
    try {
      Verification creation = apiVerificationController.createSimpleVerification(RECIPIENT);
      Verification result = apiVerificationController
          .verifyToken(creation.getMessageId(), "012345");

      // Check if verification is successfully executed
      assertNotNull(result);

      // Check if verification status is equal to success
      assertEquals("success", result.getStatus());

      // Check if statusCode is equal to 1 (success)
      assertEquals(1, result.getStatusCode().intValue());

      // Check if recipient is equal to parameter
      assertEquals(creation.getRecipient(), result.getRecipient());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the verification of a token which is already used
   *
   * @throws TwizoException because the token is already verified
   */
  @Test(expected = TwizoException.class)
  public void verifyTokenAgainTest() throws TwizoException {
    String recipient = "61123401000";

    // Create a new verification
    Verification creation = apiVerificationController.createSimpleVerification(recipient);

    // Try to verify the token
    apiVerificationController.verifyToken(creation.getMessageId(), "012345");
  }

  /**
   * This method tests the verification of a token which has expired
   *
   * @throws TwizoException because the token has expired
   */
  @Test(expected = TwizoException.class)
  public void expiredVerificationTest() throws TwizoException {
    String recipient = "61123403000";

    // Create a new verification
    Verification creation = apiVerificationController.createSimpleVerification(recipient);

    // Try to verify the token
    apiVerificationController.verifyToken(creation.getMessageId(), "012345");
  }

  /**
   * Check common params
   *
   * @param result result which will be checked
   */
  private void checkVerificationResult(Verification result) {
    // Check if verification is successful
    assertNotNull(result);

    // Check if messageId is set
    assertNotNull(result.getMessageId());

    // Check if recipient is equal to RECIPIENT variable
    assertEquals(RECIPIENT, result.getRecipient());
  }
}