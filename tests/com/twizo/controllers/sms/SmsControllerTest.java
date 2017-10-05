package com.twizo.controllers.sms;

import static org.junit.Assert.*;

import com.twizo.controllers.TestSetup;
import com.twizo.controllers.sms.ApiSmsController;
import com.twizo.dataaccess.jsonparams.SmsParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.Sms;
import com.twizo.models.SmsType;
import org.junit.Before;
import org.junit.Test;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class SmsControllerTest extends TestSetup {

  private ApiSmsController apiSmsController;
  private SmsParams smsParams;

  private String[] recipients;

  @Before
  public void setUp() throws Exception {
    apiSmsController = new ApiSmsController(NODE, TESTAPIKEY);
    recipients = new String[1];
    recipients[0] = "600123456789";

    smsParams = new SmsParams();
    smsParams.setRecipients(recipients);
    smsParams.setBody("Hello World");
    smsParams.setSender("60987654321");
  }

  /**
   * This method test the simple Sms functionality
   */
  @Test
  public void sendSmsTest() {
    try {
      Sms[] result = apiSmsController.send(smsParams, SmsType.SIMPLE);

      // Check created sms
      createdTest(result[0], 0);
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  @Test
  public void sendSimpleTest() {
    try {
      Sms result = apiSmsController.sendSimple("600123456789", "Hello World", "60987654321");

      // Check created sms
      createdTest(result, 0);
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the getStatus method of the ApiSmsController class
   */
  @Test
  public void simpleSmsGetTest() {
    try {
      Sms smsCreated = apiSmsController.send(smsParams, SmsType.SIMPLE)[0];
      Sms smsGet = apiSmsController.getStatus(smsCreated.getMessageId());

      // Check if result is received successfully and equal to created sms
      resultTest(smsCreated, smsGet);
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the creation and getStatus of a sms with multiple recipients
   */
  @Test
  public void multipleRecipientsTest() {
    recipients = new String[2];
    recipients[0] = "60123456789";
    recipients[1] = "60639462449";

    smsParams.setRecipients(recipients);

    try {
      Sms[] createdResults = apiSmsController.send(smsParams, SmsType.SIMPLE);

      for (int i = 0; i < createdResults.length; i++) {
        // Check if created sms' are correct
        createdTest(createdResults[i], i);

        // Check if status updates of created sms' are correct
        resultTest(createdResults[i], apiSmsController.getStatus(createdResults[i].getMessageId()));
      }
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the advancedSms functionality
   */
  @Test
  public void advancedSmsTest() {
    try {
      Sms[] advancedResults = apiSmsController.send(smsParams, SmsType.ADVANCED);

      for (int i = 0; i < advancedResults.length; i++) {
        // Check if created sms' are correct
        createdTest(advancedResults[i], i);

        // Check if status updates of created sms' are correct
        resultTest(advancedResults[i],
            apiSmsController.getStatus(advancedResults[i].getMessageId()));
      }
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the creation of a sms with more than 160 characters to see if the
   * concatenation is working properly
   */
  @Test
  public void concatTest() {
    // String of 203 characters including spacings
    String concatBody =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec scelerisque "
            + "orci vitae dignissim ornare. Phasellus id ante a purus ultrices congue. Sed "
            + "interdum enim in justo semper commodo. Sed interdum.";
    smsParams.setBody(concatBody);

    try {
      Sms[] concatResult = apiSmsController.send(smsParams, SmsType.SIMPLE);

      // Check if sms body is separated into 2 sms messages
      assertEquals(2, concatResult.length);

      String bodyResult = concatResult[0].getBody() + concatResult[1].getBody();

      // Check if body is sent entirely
      assertEquals(concatBody, bodyResult);
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the polling method
   */
  @Test
  public void pollingTest() {
    smsParams.setResultType(2);

    Sms[] created = null;
    Sms[] deliveryReport = null;
    try {
      created = apiSmsController.send(smsParams, SmsType.SIMPLE);
      deliveryReport = apiSmsController.getDeliveryReports();
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }

    // Check if the DeliveryReport is created
    assertNotNull(deliveryReport);

    for (int i = 0; i < created.length; i++) {
      // Check if created sms' are correct
      createdTest(created[i], i);

      // Check if resultType is set to polling
      assertEquals(2, created[i].getResultType().intValue());

      // Check if status updates of created sms' are correct
      resultTest(created[i], deliveryReport[i]);
    }
  }

  /**
   * This method tests the creation of a sms with null as parameter input
   */
  @Test(expected = TwizoException.class)
  public void nullSmsTest() throws TwizoException {

    // Execute method, TwizoException should get thrown
    apiSmsController.send(null, SmsType.SIMPLE);
  }

  /**
   * This method tests the getStatus method with a non-existing messageId as parameter,
   * TwizoException is expected
   */
  @Test(expected = TwizoException.class)
  public void wrongMessageIdTest() throws TwizoException {
    String messageId = "ThisIsIncorrect";

    // Execute method, TwizoException should get thrown
    apiSmsController.getStatus(messageId);
  }

  /**
   * This method is used to test a created sms
   *
   * @param result the created sms
   * @param counter number in recipient array
   */
  private void createdTest(Sms result, int counter) {
    // Check if sms is created successfully
    assertNotNull(result);
    assertNotNull(result.getMessageId());

    // Check if sms data is equal to smsParams data
    assertEquals(result.getSender(), smsParams.getSender());
    assertEquals(result.getRecipient(), smsParams.getRecipients()[counter]);
    assertEquals(result.getBody(), smsParams.getBody());
    assertEquals(result.getTag(), smsParams.getTag());

  }

  /**
   * This method is used to compare a received sms with a created sms
   *
   * @param expected created sms
   * @param actual received sms
   */
  private void resultTest(Sms expected, Sms actual) {
    // Check if result is received successfully
    assertNotNull(actual);
    assertEquals(expected.getMessageId(), actual.getMessageId());

    // Check if received sms is equal to created sms
    assertEquals(expected.getBody(), actual.getBody());
    assertEquals(expected.getSender(), actual.getSender());
    assertEquals(expected.getRecipient(), actual.getRecipient());
    assertEquals(expected.getTag(), actual.getTag());
  }
}