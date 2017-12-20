package com.twizo;

import com.twizo.controllers.application.ApplicationController;
import com.twizo.controllers.backup.BackupController;
import com.twizo.controllers.balance.BalanceController;
import com.twizo.controllers.biovoice.BiovoiceController;
import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.controllers.registrationwidget.RegistrationWidgetController;
import com.twizo.controllers.sms.SmsController;
import com.twizo.controllers.totp.TotpController;
import com.twizo.controllers.verification.VerificationController;
import com.twizo.controllers.widget.VerificationWidgetController;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface Twizo {

    /**
     * Get a new ApplicationController instance
     *
     * @return new ApplicationController instance
     */
    ApplicationController getApplicationController();

    /**
     * Get a new BackupController instance
     *
     * @return new BackupController Instance;
     */
    BackupController getBackupController();

    /**
     * Get a new BalanceController instance
     *
     * @return new BalanceController instance
     */
    BalanceController getBalanceController();

    /**
     * Get a new BiovoiceController instance
     *
     * @return new BiovoiceController instance
     */
    BiovoiceController getBiovoiceController();

    /**
     * Get a new NumberLookupController instance
     *
     * @return new NumberLookupController instance
     */
    NumberLookupController getNumberLookupController();

    /**
     * Get a new SmsController instance
     *
     * @return new SmsController instance
     */
    SmsController getSmsController();

    /**
     * Get a new TotpController instance
     *
     * @return new TotpController instance
     */
    TotpController getTotpController();

    /**
     * Get a new VerificationController instance
     *
     * @return new VerificationController instance
     */
    VerificationController getVerificationController();

    /**
     * Get a new VerificationWidgetController instance
     *
     * @return new VerificationWidgetController instance
     */
    VerificationWidgetController getVerificationWidgetController();

    /**
     * Get a new RegistrationWidgetController instance
     *
     * @return new RegistrationWidgetController instance
     */
    RegistrationWidgetController getRegistrationWidgetController();
}
