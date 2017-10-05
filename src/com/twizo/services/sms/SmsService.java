package com.twizo.services.sms;

import com.twizo.models.Sms;
import javafx.util.Pair;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public interface SmsService {

  /**
   * Parse the data which is returned after sending a SMS to a Sms object array
   *
   * @param data data which has to be parsed
   * @return Sms object
   */
  Sms[] parseNewSms(String data);

  /**
   * Parse the data which is returned after getting the status of a sms to a Sms object
   *
   * @param data data which has to be parsed
   * @return Sms object
   */
  Sms parseSms(String data);

  /**
   * Parse delivery report data to an array of Sms objects
   *
   * @param data data which has to parsed
   * @return Sms object array
   */
  Pair<String, Sms[]> parseDeliveryReports(String data);

}
