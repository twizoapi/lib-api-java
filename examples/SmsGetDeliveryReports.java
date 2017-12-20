import com.twizo.controllers.sms.SmsController;
import com.twizo.exceptions.SmsException;
import com.twizo.exceptions.TwizoCallException;
import com.twizo.exceptions.TwizoJsonParseException;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsGetDeliveryReports extends AbstractExample {

    /**
     * This example shows how to poll for Delivery reports
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Initialize Twizo instance
        getTwizo();

        // Get a new SmsController instance
        SmsController smsController = twizo.getSmsController();
        try {

            // Get the delivery reports by calling the getDeliveryReports method which start pollin This will only return
            // when there are sms messages created with resultType 2
            System.out.println(Arrays.asList(smsController.getDeliveryReports()));
        } catch (TwizoCallException | TwizoJsonParseException | SmsException e) {
            Logger.getLogger(SmsGetDeliveryReports.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

}
