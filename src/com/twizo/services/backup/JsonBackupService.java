package com.twizo.services.backup;

import com.google.gson.JsonObject;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.BackupCode;
import com.twizo.models.BackupCodeResult;
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
public class JsonBackupService extends AbstractService implements BackupService {

  /**
   * Parse received information to a BackupCode object
   *
   * @param data data which has to be parsed
   * @return BackupCode object
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public BackupCode parseBackupCode(String data) throws TwizoException {
    return data != null ? gson.fromJson(data, BackupCode.class) : null;
  }

  /**
   * Parse verification result to a BackupCodeResult object
   *
   * @param data data which has to be parsed
   * @return BackupCode result object
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public BackupCodeResult parseVerification(String data) throws TwizoException {
    if (data != null) {
      JsonObject all = gson.fromJson(data, JsonObject.class);
      JsonObject jsonObject = all.getAsJsonObject("_embedded").getAsJsonObject("verification");

      BackupCodeResult result = gson.fromJson(jsonObject, BackupCodeResult.class);
      result.setIdentifier(all.get("identifier").getAsString());
      result.setAmountOfCodesLeft(all.get("amountOfCodesLeft").getAsInt());
      result.setVerification(
          gson.fromJson(all.getAsJsonObject("_embedded").getAsJsonObject("verification"),
              Verification.class));

      return result;
    }
    return null;
  }
}
