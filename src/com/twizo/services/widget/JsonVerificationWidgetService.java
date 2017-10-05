package com.twizo.services.widget;

import com.google.gson.JsonObject;
import com.twizo.models.Verification;
import com.twizo.models.WidgetSession;
import com.twizo.services.AbstractService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class JsonVerificationWidgetService extends AbstractService implements
    VerificationWidgetService {

  /**
   * Parse widgetSession data received from the server to a WidgetSession object
   *
   * @param data data to parse
   * @return WidgetSession instance
   */
  @Override
  public WidgetSession parseWidgetSession(String data) {
    JsonObject jsonObject = new JsonObject();
    WidgetSession widgetSession = gson.fromJson(data, WidgetSession.class);
    widgetSession.setVerification(gson.fromJson(jsonObject.getAsJsonObject("verification"),
        Verification.class));
    return gson.fromJson(data, WidgetSession.class);
  }
}
