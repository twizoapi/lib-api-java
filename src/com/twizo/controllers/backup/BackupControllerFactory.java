package com.twizo.controllers.backup;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BackupControllerFactory {

  /**
   * Get a new instance of BackupController based on TwizoType
   *
   * @param apiHost preferred API node
   * @param apiKey API key of the user
   * @param twizoType type of Twizo functionality
   * @return BackupController instance
   */
  public BackupController getInstance(String apiHost, String apiKey, TwizoType twizoType) {
    switch (twizoType) {
      case BACKUP:
        return new ApiBackupController(apiHost, apiKey);
      default:
        return null;
    }
  }

}
