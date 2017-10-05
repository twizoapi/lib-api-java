package com.twizo;

import com.twizo.controllers.backup.BackupController;
import com.twizo.controllers.backup.BackupControllerFactory;
import com.twizo.controllers.balance.BalanceControllerFactory;
import com.twizo.controllers.numberlookup.NumberLookupControllerFactory;
import com.twizo.controllers.sms.SmsControllerFactory;
import com.twizo.controllers.balance.BalanceController;
import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.controllers.sms.SmsController;
import com.twizo.controllers.verification.VerificationController;
import com.twizo.controllers.verification.VerificationControllerFactory;
import com.twizo.controllers.widget.VerificationWidgetController;
import com.twizo.controllers.widget.VerificationWidgetControllerFactory;
import com.twizo.dataaccess.Node;
import com.twizo.exceptions.TwizoException;


/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 *
 * @author Djesse
 */
public class TwizoFactory implements Twizo {

  private String apiKey;
  private String node;

  private BalanceControllerFactory balanceControllerFactory;
  private NumberLookupControllerFactory numberLookupControllerFactory;
  private SmsControllerFactory smsControllerFactory;
  private VerificationControllerFactory verificationControllerFactory;
  private VerificationWidgetControllerFactory verificationWidgetControllerFactory;
  private BackupControllerFactory backupControllerFactory;

  /**
   * Create a new instance of the Twizo class
   *
   * @param apiKey API key to get access the Twizo API
   * @param node Preferred hosting node
   * @throws TwizoException when apiHost or apiKey is invalid
   */
  public TwizoFactory(String apiKey, Node node) throws TwizoException {

    if (apiKey != null) {
      this.apiKey = apiKey;
      balanceControllerFactory = new BalanceControllerFactory();
      numberLookupControllerFactory = new NumberLookupControllerFactory();
      smsControllerFactory = new SmsControllerFactory();
      verificationControllerFactory = new VerificationControllerFactory();
      verificationWidgetControllerFactory = new VerificationWidgetControllerFactory();
      backupControllerFactory = new BackupControllerFactory();

      switch (node) {
        case EUROPE:
          this.node = "https://api-eu-01.twizo.com";
          break;
        case ASIA:
          this.node = "https://api-asia-01.twizo.com";
          break;
        default:
          break;
      }
    } else {
      throw new TwizoException("No API key entered");
    }
  }

  /**
   * Get a new BalanceController instance
   *
   * @return new instance of BalanceController
   */
  @Override
  public BalanceController getBalanceController() {
    return balanceControllerFactory.getInstance(node, apiKey, TwizoType.ACCOUNT);
  }

  /**
   * Get a new NumberLookup instance
   *
   * @return new instance of NumberLookupController
   */
  @Override
  public NumberLookupController getNumberLookupController() {
    return numberLookupControllerFactory.getInstance(node, apiKey, TwizoType.NUMBERLOOKUP);
  }

  /**
   * Get a new SmsController instance
   *
   * @return a new instance of SmsController
   */
  @Override
  public SmsController getSmsController() {
    return smsControllerFactory.getInstance(node, apiKey, TwizoType.SMS);
  }

  /**
   * Get a new VerificationController instance
   *
   * @return new VerificationController instance
   */
  @Override
  public VerificationController getVerificationController() {
    return verificationControllerFactory.getInstance(node, apiKey, TwizoType.VERIFICATION);
  }

  /**
   * Get a new VerificationWidgetController instance
   *
   * @return new VerificationWidgetController instance
   */
  @Override
  public VerificationWidgetController getVerificationWidgetController() {
    return verificationWidgetControllerFactory.getInstance(node, apiKey, TwizoType.VERIFICATIONWIDGET);
  }

  /**
   * Get a new BackupController instance
   *
   * @return new BackupController Instance;
   */
  @Override
  public BackupController getBackupController() {
    return backupControllerFactory.getInstance(node, apiKey, TwizoType.BACKUP);
  }
}
