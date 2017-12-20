package com.twizo.controllers.backup;

import com.twizo.exceptions.BackupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
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
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    BackupCode createBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException, BackupException;

    /**
     * Verify an existing backup code
     *
     * @param identifier unique identifier of the user
     * @param backupCode backup code to be verified
     * @return result of the verification parsed to a BackupCodeResult object
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    BackupCodeResult verifyBackupCode(String identifier, String backupCode) throws TwizoCallException, TwizoJsonParseException, BackupException;

    /**
     * Get the remaining backup codes of a user
     *
     * @param identifier unique identifier of the user
     * @return BackupCode instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    BackupCode getRemainingBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException, BackupException;

    /**
     * Update the backup codes of the user and parse the new ones to a BackupCode object
     *
     * @param identifier unique identifier of the user
     * @return BackupCode instance with backup codes
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    BackupCode updateBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException, BackupException;

    /**
     * Delete all existing backup codes of a user and parse the result to a BackupCode object
     *
     * @param identifier unique identifier of the user
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    void deleteBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException, BackupException;

}
