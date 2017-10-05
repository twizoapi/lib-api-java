package com.twizo.services.backup;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BackupServiceFactory {

  /**
   * Get a new instance of JsonBackupService
   *
   * @return JsonBackupService instance
   */
  public BackupService getInstance() {
    return new JsonBackupService();
  }

}
