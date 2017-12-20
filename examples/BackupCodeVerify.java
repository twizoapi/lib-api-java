import com.twizo.controllers.backup.BackupController;
import com.twizo.exceptions.BackupException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class BackupCodeVerify extends AbstractExample {

    /**
     * This example shows to to verify a backup code
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new backupController instance
        BackupController backupController = twizo.getBackupController();
        try {
            // Get a backupcode identifier
            System.out.println("Enter a backupcode identifier");
            String identifier = input.readLine();

            // Get a backup code
            System.out.println("Enter a backupcode");
            String backupCode = input.readLine();

            // Call the verifyBackupCode method with a identifier and token (backupCode) to verify
            System.out.println(backupController.verifyBackupCode(identifier, backupCode));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | BackupException e) {
            Logger.getLogger(BackupCodeVerify.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
