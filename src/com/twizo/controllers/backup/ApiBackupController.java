package com.twizo.controllers.backup;

import com.google.gson.JsonSyntaxException;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.Worker;
import com.twizo.dataaccess.jsonparams.BackupParams;
import com.twizo.exceptions.BackupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.BackupCode;
import com.twizo.models.BackupCodeResult;
import com.twizo.services.backup.BackupService;
import com.twizo.services.backup.JsonBackupService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class ApiBackupController extends TwizoController implements BackupController {

    private final BackupService backupService;

    private static final String URLPREFIX = "backupcode";

    /**
     * Create a new ApiBackupController instance
     *
     * @param worker Api worker
     */
    public ApiBackupController(Worker worker) {
        super(worker);

        backupService = new JsonBackupService();
    }

    /**
     * Create new backup codes and parse them to a BackupCode object
     *
     * @param identifier unique identifier of the user
     * @return BackupCode instance with backup codes
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    @Override
    public BackupCode createBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException,
            BackupException {
        BackupParams backupParams = new BackupParams(identifier);

        return backupService.parseBackupCode(worker.execute(URLPREFIX, processParams(backupParams), RequestType.POST));
    }

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
    @Override
    public BackupCodeResult verifyBackupCode(String identifier, String backupCode) throws TwizoCallException,
            TwizoJsonParseException, BackupException {
        return backupService.parseVerification(worker
                .execute(String.format("%s/%s?token=%s", URLPREFIX, identifier, backupCode), null,
                        RequestType.GET));
    }

    /**
     * Get the remaining backup codes of a user
     *
     * @param identifier unique identifier of the user
     * @return BackupCode instance
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    @Override
    public BackupCode getRemainingBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException,
            BackupException {
        return backupService.parseBackupCode(
                worker.execute(String.format("%s/%s", URLPREFIX, identifier), null, RequestType.GET));
    }

    /**
     * Update the backup codes of the user and parse the new ones to a BackupCode object
     *
     * @param identifier unique identifier of the user
     * @return BackupCode instance with backup codes
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    @Override
    public BackupCode updateBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException,
            BackupException {
        BackupParams backupParams = new BackupParams(identifier);

        return backupService.parseBackupCode(worker
                .execute(String.format("%s/%s", URLPREFIX, identifier), processParams(backupParams),
                        RequestType.PUT));
    }

    /**
     * Delete all existing backup codes of a user and parse the result to a BackupCode object
     *
     * @param identifier unique identifier of the user
     * @throws TwizoCallException      when something goes wrong during calling the API
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     * @throws BackupException         when something goes wrong during the process
     */
    @Override
    public void deleteBackupCodes(String identifier) throws TwizoCallException, TwizoJsonParseException,
            BackupException {
        worker.execute(String.format("%s/%s", URLPREFIX, identifier), null, RequestType.DELETE);
    }

    /**
     * This method increases the chance the parameters added to the creation of backup codes are
     * correct and properly formatted
     *
     * @param params Parameters which will be added to the request
     * @return Properly formatted JSON String with parameters
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    private String processParams(BackupParams params) throws TwizoJsonParseException {
        if (params != null) {
            params.setIdentifier(params.getIdentifier());
            try {
                return gson.toJson(params);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        return null;
    }
}
