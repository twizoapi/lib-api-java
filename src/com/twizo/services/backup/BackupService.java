package com.twizo.services.backup;

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
public interface BackupService {

  /**
   * Parse received information to a BackupCode object
   *
   * @param data data which has to be parsed
   * @return BackupCode object
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCode parseBackupCode(String data) throws TwizoException;

  /**
   * Parse verification result to a BackupCodeResult object
   *
   * @param data data which has to be parsed
   * @return BackupCode result object
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCodeResult parseVerification(String data) throws TwizoException;
}
