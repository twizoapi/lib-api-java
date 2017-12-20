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
public class BackupCodeCreate extends AbstractExample{

    /**
     * This example shows how to create a new set of backup codes
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

            // Call the createBackupCodes method with a identifier to create new backup codes
            System.out.println(backupController.createBackupCodes(identifier));
        } catch (TwizoCallException | TwizoJsonParseException | IOException | BackupException e) {
            Logger.getLogger(BackupCodeCreate.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
