package com.twizo.controllers.backup;

import com.twizo.exceptions.TwizoException;
import com.twizo.models.BackupCode;
import com.twizo.models.BackupCodeResult;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface BackupController {

  /**
   * Create new backup codes and parse them to a BackupCode object
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance with backup codes
   */
  BackupCode createBackupCodes(String identifier) throws TwizoException;

  /**
   * Verify an existing backup code
   *
   * @param identifier unique identifier of the user
   * @param backupCode backup code to be verified
   * @return result of the verification parsed to a BackupCodeResult object
   */
  BackupCodeResult verifyBackupCode(String identifier, String backupCode) throws TwizoException;

  /**
   * Get the remaining backup codes of a user
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance
   */
  BackupCode getRemainingBackupCodes(String identifier) throws TwizoException;

  /**
   * Update the backup codes of the user and parse the new ones to a BackupCode object
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance with backup codes
   */
  BackupCode updateBackupCodes(String identifier) throws TwizoException;

  /**
   * Delete all existing backup codes of a user and parse the result to a BackupCode object
   *
   * @param identifier unique identifier of the user
   */
  void deleteBackupCodes(String identifier) throws TwizoException;

}
