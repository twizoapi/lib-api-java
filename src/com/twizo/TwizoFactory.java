package com.twizo;

import com.twizo.controllers.application.ApiApplicationController;
import com.twizo.controllers.application.ApplicationController;
import com.twizo.controllers.backup.ApiBackupController;
import com.twizo.controllers.backup.BackupController;
import com.twizo.controllers.balance.ApiBalanceController;
import com.twizo.controllers.balance.BalanceController;
import com.twizo.controllers.biovoice.ApiBiovoiceController;
import com.twizo.controllers.biovoice.BiovoiceController;
import com.twizo.controllers.numberlookup.ApiNumberLookupController;
import com.twizo.controllers.numberlookup.NumberLookupController;
import com.twizo.controllers.registrationwidget.ApiRegistrationWidgetController;
import com.twizo.controllers.registrationwidget.RegistrationWidgetController;
import com.twizo.controllers.sms.ApiSmsController;
import com.twizo.controllers.sms.SmsController;
import com.twizo.controllers.totp.ApiTotpController;
import com.twizo.controllers.totp.TotpController;
import com.twizo.controllers.verification.ApiVerificationController;
import com.twizo.controllers.verification.VerificationController;
import com.twizo.controllers.widget.ApiVerificationWidgetController;
import com.twizo.controllers.widget.VerificationWidgetController;
import com.twizo.dataaccess.Node;
import com.twizo.dataaccess.Worker;
import com.twizo.exceptions.ApplicationException;


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

    private Worker worker;

    private ApplicationController applicationController;

    private BackupController backupController;

    private BalanceController balanceController;

    private BiovoiceController biovoiceController;

    private NumberLookupController numberLookupController;

    private SmsController smsController;

    private TotpController totpController;

    private VerificationController verificationController;

    private VerificationWidgetController verificationWidgetController;

    private RegistrationWidgetController registrationWidgetController;

    /**
     * Create a new instance of the Twizo class
     *
     * @param apiKey API key to get access the Twizo API
     * @param node   Preferred hosting node
     * @throws ApplicationException when apiHost or apiKey is invalid
     */
    public TwizoFactory(String apiKey, Node node) throws ApplicationException {

        if (apiKey != null) {

            String apiNode = null;
            switch (node) {
                case EUROPE:
                    apiNode = "https://api-eu-01.twizo.com";
                    break;
                case ASIA:
                    apiNode = "https://api-asia-01.twizo.com";
                    break;
                default:
                    break;
            }

            this.worker = new Worker(apiNode, apiKey);
        } else {
            throw new ApplicationException("No API key entered");
        }
    }

    /**
     * Get a new ApplicationController instance
     *
     * @return new ApplicationController instance
     */
    @Override
    public ApplicationController getApplicationController() {
        if (applicationController == null) {
            return applicationController = new ApiApplicationController(worker);
        }
        return applicationController;
    }


    /**
     * Get a new BackupController instance
     *
     * @return new BackupController Instance;
     */
    @Override
    public BackupController getBackupController() {
        if (backupController == null) {
            return backupController = new ApiBackupController(worker);
        }
        return backupController;
    }

    /**
     * Get a new BalanceController instance
     *
     * @return new instance of BalanceController
     */
    @Override
    public BalanceController getBalanceController() {
        if (balanceController == null) {
            return balanceController = new ApiBalanceController(worker);
        }
        return balanceController;
    }

    /**
     * Get a new BiovoiceController instance
     *
     * @return new BiovoiceController instance
     */
    @Override
    public BiovoiceController getBiovoiceController() {
        if (biovoiceController == null) {
            return biovoiceController = new ApiBiovoiceController(worker);
        }
        return biovoiceController;
    }

    /**
     * Get a new NumberLookup instance
     *
     * @return new instance of NumberLookupController
     */
    @Override
    public NumberLookupController getNumberLookupController() {
        if (numberLookupController == null) {
            return numberLookupController = new ApiNumberLookupController(worker);
        }
        return numberLookupController;
    }

    /**
     * Get a new SmsController instance
     *
     * @return a new instance of SmsController
     */
    @Override
    public SmsController getSmsController() {
        if (smsController == null) {
            return smsController = new ApiSmsController(worker);
        }
        return smsController;
    }

    /**
     * Get a new TotpController instance
     *
     * @return new TotpController instance
     */
    @Override
    public TotpController getTotpController() {
        if (totpController == null) {
            return totpController = new ApiTotpController(worker);
        }
        return totpController;
    }

    /**
     * Get a new VerificationController instance
     *
     * @return new VerificationController instance
     */
    @Override
    public VerificationController getVerificationController() {
        if (verificationController == null) {
            return verificationController = new ApiVerificationController(worker);
        }
        return verificationController;
    }

    /**
     * Get a new VerificationWidgetController instance
     *
     * @return new VerificationWidgetController instance
     */
    @Override
    public VerificationWidgetController getVerificationWidgetController() {
        if (verificationWidgetController == null) {
            return verificationWidgetController = new ApiVerificationWidgetController(worker);
        }
        return verificationWidgetController;
    }

    /**
     * Get a new RegistrationWidgetController instance
     *
     * @return new RegistrationWidgetController instance
     */
    @Override
    public RegistrationWidgetController getRegistrationWidgetController() {
        if (registrationWidgetController == null) {
            return registrationWidgetController = new ApiRegistrationWidgetController(worker);
        }
        return registrationWidgetController;
    }
}
