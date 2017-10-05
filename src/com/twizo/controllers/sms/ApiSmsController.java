package com.twizo.controllers.sms;

import com.twizo.TwizoType;
import com.twizo.controllers.TwizoController;
import com.twizo.dataaccess.RequestType;
import com.twizo.dataaccess.jsonparams.SmsParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Sms;
import com.twizo.models.SmsType;
import com.twizo.services.sms.SmsServiceFactory;
import com.twizo.services.sms.SmsService;
import javafx.util.Pair;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
class ApiSmsController extends TwizoController implements SmsController {

  private final SmsService smsService;

  ApiSmsController(String apiHost, String apiKey) {
    super(apiHost, apiKey);

    SmsServiceFactory smsServiceFactory = new SmsServiceFactory();
    smsService = smsServiceFactory.getInstance(TwizoType.SMS);
  }

  /**
   * Create and sendSimple a new Sms and parse the result to a Sms object
   *
   * @param params Parameters which will be added to the request
   * @param type type of Sms (simple/advanced)
   * @return Result of Sms creation
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Sms[] send(SmsParams params, SmsType type) throws TwizoException {
    switch (type) {
      case SIMPLE:
        return smsService.parseNewSms(
            worker.execute("sms/submitsimple", processParams(params), RequestType.POST));
      case ADVANCED:
        if (params.getDcs() != null && isBinary(params)) {
          params.setBody(Integer.decode(params.getBody()).toString().toUpperCase());
        }
        return smsService
            .parseNewSms(
                worker.execute("sms/submit", processParams(params), RequestType.POST));
      default:
        return new Sms[0];
    }
  }

  /**
   * Send a simple sms
   *
   * @param recipient recipient of the sms
   * @param body body of the sms
   * @param sender sender of the sms
   * @return Result of sms creation
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Sms sendSimple(String recipient, String body, String sender) throws TwizoException {
    SmsParams params = new SmsParams();
    params.setBody(body);
    params.setSender(sender);
    params.setRecipients(new String[]{recipient});

    return smsService.parseNewSms(
        worker.execute("sms/submitsimple", processParams(params),
            RequestType.POST))[0];
  }

  /**
   * Get the status of a Sms and parse it to a Sms object
   *
   * @param messageId Id of Sms
   * @return Result of GET request to get information about a specific sms
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Sms getStatus(String messageId) throws TwizoException {
    return smsService.parseSms(
        worker.execute(String.format("sms/submit/%s", messageId), null, RequestType.GET));
  }

  /**
   * Poll results about Sms delivery reports.
   *
   * @return Array of Sms objects
   * @throws TwizoException when something goes wrong during the process
   */
  @Override
  public Sms[] getDeliveryReports() throws TwizoException {
    Pair<String, Sms[]> result = smsService
        .parseDeliveryReports(worker.execute("sms/poll", null, RequestType.GET));

    // Delete the delivery reports by using the batchId
    worker.execute(String.format("sms/poll/%s", result.getKey()), null,
        RequestType.DELETE);

    return result.getValue();
  }

  /**
   * This method increases the change parameters added to request are properly formatted
   *
   * @param params parameters to process
   * @return Json string with parameters
   * @throws TwizoException when a constraint violation occurs
   */
  private String processParams(SmsParams params) throws TwizoException {
    if (params != null) {
      // Check if mandatory parameter value recipients is filled in and matches constraints
      if (params.getRecipients() != null || params.getRecipients().length < 1
          || params.getRecipients().length > 1000) {

        String[] recipients = params.getRecipients();
        for (int i = 0; i < recipients.length; i++) {
          if (recipients[i] != null) {
            recipients[i] = processPhoneNumber(recipients[i]);
          }
        }
      } else {
        throw new TwizoException("Recipients doesn't match constraints");
      }

      return gson.toJson(params);
    } else {
      throw new TwizoException("Params can't be null");
    }
  }

  /**
   * Check if sms is binary for hexadecimal conversion
   *
   * @param params Parameter object to be checked
   * @return true if binary, false if not
   */
  private boolean isBinary(SmsParams params) {
    boolean binary = false;

    if ((params.getDcs() & 200) == 0) {
      binary = (params.getDcs() & 4) > 0;
    } else if ((params.getDcs() & 248) == 240) {
      binary = (params.getDcs() & 4) > 0;
    }

    return binary;
  }
}
