package com.twizo.controllers.backup;

import static org.junit.Assert.*;

import com.twizo.controllers.TestSetup;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.BackupCode;
import com.twizo.models.BackupCodeResult;
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
public class BackupTest extends TestSetup {

  private ApiBackupController apiBackupController;
  private static final String IDENTIFIER = "60123456789";

  @Before
  public void setUp() {
    apiBackupController = new ApiBackupController(NODE, TESTAPIKEY);
  }

  /**
   * This method the createBackupCode method under normal circumstances
   */
  @Test
  public void createTest() {
    // Make sure to delete all existing backup codes to be able to create new
    try {
      deleteExistingCodes();
      executeCreateTest();
    } catch (TwizoException e) {
      executeCreateTest();
    }
  }

  /**
   * Execute createTest
   */
  private void executeCreateTest() {
    try {
      BackupCode result = apiBackupController.createBackupCodes(IDENTIFIER);

      // Check if the amount of created backup codes equals 10
      assertEquals(10, result.getAmountOfCodesLeft().intValue());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the verification of a backupCode
   */
  @Test
  public void verifyBackupCodeTest() {
    try {
      deleteExistingCodes();
      executeVerifyBackupCodeTest();
    } catch (TwizoException e) {
      executeVerifyBackupCodeTest();
    }
  }

  /**
   * Execute verifyBackupCodeTest
   */
  private void executeVerifyBackupCodeTest() {
    try {
      // Create new backup codes
      BackupCode backupCode = apiBackupController.createBackupCodes(IDENTIFIER);

      // Verify first backup code
      BackupCodeResult result = apiBackupController
          .verifyBackupCode(IDENTIFIER, backupCode.getCodes()[0]);

      // Check if verification is successful
      assertEquals("success", result.getVerification().getStatus());

      // Check if amount of backup codes got decreased by 1
      assertEquals(9, result.getAmountOfCodesLeft().intValue());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the verification of an invalid backupCode. A TwizoException is expected
   */
  @Test(expected = TwizoException.class)
  public void invalidCodeTest() throws TwizoException {
    String invalidBackupCode = "ThisIsInvalid";

    // Execute method, TwizoException should get thrown
    apiBackupController.createBackupCodes(invalidBackupCode);
  }

  /**
   * This method tests the getRemainingBackupCodes method
   */
  @Test
  public void getRemainingBackupCodesTest() {
    try {
      deleteExistingCodes();
      executeRemainingBackupCodeTest();
    } catch (TwizoException e) {
      executeRemainingBackupCodeTest();
    }
  }

  /**
   * Execute getRemainingBackupCodeTest
   */
  private void executeRemainingBackupCodeTest() {
    try {
      apiBackupController.createBackupCodes(IDENTIFIER);
      BackupCode result = apiBackupController.getRemainingBackupCodes(IDENTIFIER);

      // Check if amount of backup codes is equal to 10
      assertEquals(10, result.getAmountOfCodesLeft().intValue());
    } catch (TwizoException e1) {
      e1.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the deletion of backup codes
   *
   * @throws TwizoException because no backup codes can be found
   */
  @Test(expected = TwizoException.class)
  public void deleteBackupCodesTest() throws TwizoException {
    deleteExistingCodes();

    BackupCode created = apiBackupController.createBackupCodes(IDENTIFIER);

    // Check if new backup codes are created successfully
    assertEquals(10, created.getAmountOfCodesLeft().intValue());

    apiBackupController.deleteBackupCodes(IDENTIFIER);
    apiBackupController.getRemainingBackupCodes(IDENTIFIER);
  }

  /**
   * This method deletes all existing backup codes on the server
   */
  private void deleteExistingCodes() throws TwizoException {
    apiBackupController.deleteBackupCodes(IDENTIFIER);
  }
}