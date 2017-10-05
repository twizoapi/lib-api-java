package com.twizo.services.sms;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsServiceFactory {

  public SmsService getInstance(TwizoType twizoType) {
    switch (twizoType) {
      case SMS:
        return new JsonSmsService();
      default:
        return null;
    }
  }

}
