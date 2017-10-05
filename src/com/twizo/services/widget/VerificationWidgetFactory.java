package com.twizo.services.widget;

import com.twizo.TwizoType;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class VerificationWidgetFactory {

  public VerificationWidgetService getInstance(TwizoType twizoType) {
    switch (twizoType) {
      case VERIFICATIONWIDGET:
        return new JsonVerificationWidgetService();
      default:
        return null;
    }
  }
}
