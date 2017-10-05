package com.twizo.controllers.backup;

import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.jsonparams.BackupParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.BackupCode;
import com.twizo.models.BackupCodeResult;
import com.twizo.services.backup.BackupService;
import com.twizo.services.backup.BackupServiceFactory;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class ApiBackupController extends TwizoController implements BackupController {

  private final BackupService backupService;

  private static final String URLPREFIX = "backupcode";

  /**
   * Create a new BackupController instance
   *
   * @param apiHost Preferred API Node
   * @param apiKey Accesskey to the API
   */
  ApiBackupController(String apiHost, String apiKey) {
    super(apiHost, apiKey);

    BackupServiceFactory backupServiceFactory = new BackupServiceFactory();
    backupService = backupServiceFactory.getInstance();
  }

  /**
   * Create new backup codes and parse them to a BackupCode object
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance with backup codes
   */
  @Override
  public BackupCode createBackupCodes(String identifier) throws TwizoException {
    BackupParams backupParams = new BackupParams(identifier);

    return backupService.parseBackupCode(
        worker.execute(String.format("%s", URLPREFIX), processParams(backupParams),
            RequestType.POST));
  }

  /**
   * Verify an existing backup code
   *
   * @param identifier unique identifier of the user
   * @param backupCode backup code to be verified
   * @return result of the verification parsed to a BackupCodeResult object
   */
  @Override
  public BackupCodeResult verifyBackupCode(String identifier, String backupCode)
      throws TwizoException {
    return backupService.parseVerification(worker
        .execute(String.format("%s/%s?token=%s", URLPREFIX, identifier, backupCode), null,
            RequestType.GET));
  }

  /**
   * Get the remaining backup codes of a user
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance
   */
  @Override
  public BackupCode getRemainingBackupCodes(String identifier) throws TwizoException {
    return backupService.parseBackupCode(
        worker.execute(String.format("%s/%s", URLPREFIX, identifier), null, RequestType.GET));
  }

  /**
   * Update the backup codes of the user and parse the new ones to a BackupCode object
   *
   * @param identifier unique identifier of the user
   * @return BackupCode instance with backup codes
   */
  @Override
  public BackupCode updateBackupCodes(String identifier) throws TwizoException {
    BackupParams backupParams = new BackupParams(identifier);

    return backupService.parseBackupCode(worker
        .execute(String.format("%s/%s", URLPREFIX, identifier), processParams(backupParams),
            RequestType.PUT));
  }

  /**
   * Delete all existing backup codes of a user and parse the result to a BackupCode object
   *
   * @param identifier unique identifier of the user
   */
  @Override
  public void deleteBackupCodes(String identifier) throws TwizoException {
    worker.execute(String.format("%s/%s", URLPREFIX, identifier), null, RequestType.DELETE);
  }

  /**
   * This method increases the chance the parameters added to the creation of backup codes are
   * correct and properly formatted
   *
   * @param params Parameters which will be added to the request
   * @return Properly formatted JSON String with parameters
   */
  private String processParams(BackupParams params) {
    if (params != null) {
      params.setIdentifier(processPhoneNumber(params.getIdentifier()));
      return gson.toJson(params);
    }
    return null;
  }
}
