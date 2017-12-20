package com.twizo.services.backup;

import com.twizo.exceptions.BackupException;
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
public interface BackupService {

    /**
     * Parse received information to a BackupCode object
     *
     * @param data data which has to be parsed
     * @return BackupCode object
     * @throws BackupException         when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    BackupCode parseBackupCode(String data) throws TwizoJsonParseException, BackupException;

    /**
     * Parse verification result to a BackupCodeResult object
     *
     * @param data data which has to be parsed
     * @return BackupCode result object
     * @throws BackupException         when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    BackupCodeResult parseVerification(String data) throws TwizoJsonParseException, BackupException;
}
