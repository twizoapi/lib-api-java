package com.twizo.controllers.widget;

import com.twizo.TwizoType;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.WidgetSession;
import com.twizo.services.widget.VerificationWidgetFactory;
import com.twizo.services.widget.VerificationWidgetService;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class ApiVerificationWidgetController extends TwizoController implements
    VerificationWidgetController {

  private final VerificationWidgetService verificationWidgetService;

  ApiVerificationWidgetController(String apiHost, String apiKey) {
    super(apiHost, apiKey);

    VerificationWidgetFactory verificationWidgetFactory = new VerificationWidgetFactory();
    verificationWidgetService = verificationWidgetFactory.getInstance(TwizoType.VERIFICATIONWIDGET);
  }

  /**
   * Create a new widgetSession and parse the result to a WidgetSession object
   *
   * @param params WidgetSessionParams object to add parameters to request
   * @return WidgetSession object
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public WidgetSession createWidgetSession(WidgetSessionParams params) throws TwizoException {
    return verificationWidgetService.parseWidgetSession(
        worker.execute("widget/session", processParams(params), RequestType.POST));
  }

  /**
   * Create a new widgetSession and send the result back as a Json String
   *
   * @param params WidgetSessionParams object to add parameters to request
   * @return Json String
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public String createWidgetSessionJson(WidgetSessionParams params) throws TwizoException {
    return worker.execute("widget/session", processParams(params), RequestType.POST);
  }

  /**
   * Get the status of a session and parse the result to a WidgetSession object
   *
   * @param sessionToken identifier of the session
   * @param recipient phone number specified for the session
   * @param identifier backup code identifier
   * @return WidgetSession object
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public WidgetSession getSessionStatus(String sessionToken, String recipient, String identifier,
      WidgetSessionType type) throws TwizoException {

    switch (type) {
      case RECIPIENT:
        return verificationWidgetService.parseWidgetSession(
            worker.execute(String.format("widget/session/%s?recipient=%s", sessionToken, recipient),
                null, RequestType.GET));
      case BACKUPCODE:
        return verificationWidgetService.parseWidgetSession(
            worker.execute(String
                    .format("widget/session/%s?backupCodeIdentifier=%s", sessionToken, identifier),
                null, RequestType.GET));
      case BOTH:
        return verificationWidgetService.parseWidgetSession(
            worker.execute(String
                    .format("widget/session/%s?recipient=%s&backupCodeIdentifier=%s", sessionToken,
                        recipient, identifier), null,
                RequestType.GET));
      default:
        return null;
    }
  }

  /**
   * Get the status of a session and send the result back as a Json String
   *
   * @param sessionToken identifier of the session
   * @param recipient phone number specified for the session
   * @param identifier backup code identifier
   * @return Json String
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public String getSessionStatusJson(String sessionToken, String recipient, String identifier,
      WidgetSessionType type) throws TwizoException {
    switch (type) {
      case RECIPIENT:
        return worker
            .execute(String.format("widget/session/%s?recipient=%s", sessionToken, recipient),
                null, RequestType.GET);
      case BACKUPCODE:
        return worker.execute(String
                .format("widget/session/%s?backupCodeIdentifier=%s", sessionToken, identifier),
            null, RequestType.GET);
      case BOTH:
        return worker.execute(String
                .format("widget/session/%s?recipient=%s&backupCodeIdentifier=%s", sessionToken,
                    recipient, identifier), null,
            RequestType.GET);
      default:
        return null;
    }
  }

  /**
   * Process parameters for request
   *
   * @param params params to process
   * @return parameters in json format
   * @throws TwizoException when something goes wrong during the process
   */

  private String processParams(WidgetSessionParams params) throws TwizoException {
    if (params != null) {

      // Process phone number
      if (params.getRecipient() != null) {
        params.setRecipient(processPhoneNumber(params.getRecipient()));
      }

      return gson.toJson(params);
    }
    throw new TwizoException("Parameters cannot be null");
  }
}
