import com.twizo.Twizo;
import com.twizo.controllers.backup.BackupController;
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
class BackupExample {

  private final BackupController backupController;
  private final String identifier = "60123456789";

  BackupExample(Twizo twizo) {

    // Get a new BackupController instance
    backupController = twizo.getBackupController();
  }

  /**
   * This example shows how to create a new set of backup codes
   *
   * @return BackupCode instance with new backup codes
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCode createBackupCodes() throws TwizoException {

    // Call the createBackupCodes method with a phoneNumber as identifier to create new backup codes
    return backupController.createBackupCodes(identifier);
  }

  /**
   * This example shows to to verify a backup code
   *
   * @param backupCode backup code which will be verified
   * @return BackupCodeResult instance
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCodeResult verifyBackupCode(String backupCode) throws TwizoException {

    // Call the verifyBackupCode method with a phoneNumber and token (backupCode) as identifier to
    // verify a token
    return backupController.verifyBackupCode(identifier, backupCode);
  }

  /**
   * This example shows how to get the remaining amount of backup codes
   *
   * @return BackupCode instance
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCode getRemainingCodes() throws TwizoException {

    // Call the getRemainingBackupCodes method with a phoneNumber as identifier to get the remaining
    // amount of backup codes
    return backupController.getRemainingBackupCodes(identifier);
  }

  /**
   * This example shows how to update the backup codes
   *
   * @return BackupCode instance with new backup codes
   * @throws TwizoException when something goes wrong during the process
   */
  BackupCode updateBackupCodes() throws TwizoException {
    // Call the updateBackupCodes method with a phoneNumber as identifier to update the user's
    // backup codes
    return backupController.updateBackupCodes(identifier);
  }

  /**
   * This example shows how to delete a user's backup codes
   *
   * @throws TwizoException when something goes wrong during the process
   */
  void deleteBackupCodes() throws TwizoException {
    // Call the deleteBackupCodes method with a phoneNumber as identifier to delete the user's
    // backup codes
    backupController.deleteBackupCodes(identifier);
  }

}
