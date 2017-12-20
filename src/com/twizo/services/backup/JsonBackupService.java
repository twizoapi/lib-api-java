package com.twizo.services.backup;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.BackupException;
import com.twizo.exceptions.TwizoJsonParseException;
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
     * @throws BackupException         when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public BackupCode parseBackupCode(String data) throws TwizoJsonParseException, BackupException {
        if (data != null) {
            try {
                return gson.fromJson(data, BackupCode.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new BackupException("Twizo didn't response as expected, please try again");
    }

    /**
     * Parse verification result to a BackupCodeResult object
     *
     * @param data data which has to be parsed
     * @return BackupCode result object
     * @throws BackupException         when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during JSON parsing
     */
    @Override
    public BackupCodeResult parseVerification(String data) throws TwizoJsonParseException, BackupException {
        if (data != null) {
            try {
                JsonObject all = gson.fromJson(data, JsonObject.class);
                JsonObject jsonObject = all.getAsJsonObject("_embedded").getAsJsonObject("verification");

                BackupCodeResult result = gson.fromJson(jsonObject, BackupCodeResult.class);

                // Set properties
                result.setIdentifier(all.get("identifier").getAsString());
                result.setAmountOfCodesLeft(all.get("amountOfCodesLeft").getAsInt());
                result.setVerification(
                        gson.fromJson(all.getAsJsonObject("_embedded").getAsJsonObject("verification"),
                                Verification.class));

                return result;
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }

        }
        throw new BackupException("Twizo didn't response as expected, please try again");
    }
}
