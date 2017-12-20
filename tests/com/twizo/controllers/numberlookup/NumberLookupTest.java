//package com.twizo.controllers.numberlookup;
//
//import com.twizo.controllers.TestSetup;
//import com.twizo.controllers.numberlookup.ApiNumberLookupController;
//import com.twizo.dataaccess.jsonparams.NumberLookupParams;
//import com.twizo.exceptions.TwizoException;
//import com.twizo.models.NumberLookup;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
///**
// * This file is part of the Twizo Java API
// *
// * For the full copyright and licence information, please view the Licence file that was distributed
// * with this source code
// *
// * (c) Twizo - info@twizo.com
// */
//public class NumberLookupTest extends TestSetup {
//
//  private ApiNumberLookupController apiNumberLookupController;
//  private String[] numbers;
//  private NumberLookupParams numberLookupParams;
//
//  @Before
//  public void setUp() {
//    apiNumberLookupController = new ApiNumberLookupController(NODE, TESTAPIKEY);
//    numbers = new String[2];
//    numbers[0] = "60987654321";
//    numbers[1] = "60123456789";
//
//    numberLookupParams = new NumberLookupParams();
//    numberLookupParams.setNumbers(numbers);
//    numberLookupParams.setTag("hello");
//    numberLookupParams.setValidity(259200);
//    numberLookupParams.setResultType(0);
//  }
//
//  /**
//   * This method tests the creation and status request of a single NumberLookup
//   */
//  @Test
//  public void singleSendAndGetTest() {
//    numberLookupParams.setNumbers(new String[]{"60987654321"});
//
//    NumberLookup[] created = new NumberLookup[0];
//    try {
//      created = apiNumberLookupController.createNumberlookup(numberLookupParams);
//    } catch (TwizoException e) {
//      e.printStackTrace();
//    }
//
//    // Check if NumberLookup is successfully created
//    assertEquals(created[0].getNumber(), numbers[0]);
//    assertEquals(created[0].getStatus(), "no status");
//
//    NumberLookup numberLookupResult = null;
//    try {
//      numberLookupResult = apiNumberLookupController
//          .getNumberLookup(created[0].getMessageId());
//    } catch (TwizoException e) {
//      e.printStackTrace();
//    }
//
//    // Compare results of NumberLookup
//    checkNumberLookup(created[0], numberLookupResult);
//  }
//
//  /**
//   * This method test the NumberLookup with only a phone number as parameter
//   */
//  @Test
//  public void simpleNumberLookupTest() {
//    try {
//      // Create a simple number lookup
//      NumberLookup numberLookup = apiNumberLookupController.createSimpleNumberLookup("60987654321");
//
//      assertNotNull(numberLookup);
//      assertEquals(numbers[0], numberLookup.getNumber());
//      assertNotNull(numberLookup.getMessageId());
//    } catch (TwizoException e) {
//      e.printStackTrace();
//      fail();
//    }
//  }
//
//  /**
//   * This method tests the creation and status request of multiple NumberLookups
//   */
//  @Test
//  public void multipleSendAndGetTest() {
//    NumberLookup[] created = new NumberLookup[0];
//    try {
//      created = apiNumberLookupController.createNumberlookup(numberLookupParams);
//    } catch (TwizoException e) {
//      e.printStackTrace();
//      fail();
//    }
//
//    // Check if first NumberLookup is successfully created
//    assertEquals(created[0].getNumber(), numbers[0]);
//    assertEquals(created[0].getStatus(), "no status");
//
//    // Check if second NumberLookup is successfully created
//    assertEquals(created[1].getNumber(), numbers[1]);
//    assertEquals(created[1].getStatus(), "no status");
//
//    NumberLookup[] numberLookupResults = new NumberLookup[2];
//
//    // Get results of NumberLookups
//    for (int i = 0; i < 2; i++) {
//      try {
//        numberLookupResults[i] = apiNumberLookupController
//            .getNumberLookup(created[i].getMessageId());
//      } catch (TwizoException e) {
//        e.printStackTrace();
//        fail();
//      }
//    }
//
//    // Compare results of first NumberLookup
//    checkNumberLookup(created[0], numberLookupResults[0]);
//
//    // Compare results of second NumberLookup
//    checkNumberLookup(created[1], numberLookupResults[1]);
//  }
//
//  /**
//   * This method tests the NumberLookup creation when entering null as parameters, a TwizoException
//   * is expected
//   */
//  @Test(expected = TwizoException.class)
//  public void nullInputTest() throws TwizoException {
//    apiNumberLookupController.createNumberlookup(null);
//  }
//
//  /**
//   * This method tests the parseNumberLookup method when entering a wrong MessageId, a
//   * TwizoException is expected
//   */
//  @Test(expected = TwizoException.class)
//  public void wrongMessageIdTest() throws TwizoException {
//    String messageId = "WrongMessageId";
//
//    apiNumberLookupController.getNumberLookup(messageId);
//  }
//
//  /**
//   * This method tests the polling method
//   */
//  @Test
//  public void pollingTest() {
//    // Create new NumberLookup with Polling(2) resultType
//    numberLookupParams.setNumbers(numbers);
//    numberLookupParams.setTag("testing");
//    numberLookupParams.setResultType(2);
//
//    NumberLookup[] result = null;
//    try {
//      apiNumberLookupController.createNumberlookup(numberLookupParams);
//      result = apiNumberLookupController.getResults();
//    } catch (TwizoException e) {
//      e.printStackTrace();
//      fail();
//    }
//
//    // Check if result is not equal to null
//    assertNotNull(result);
//
//    for (NumberLookup numberLookup : result) {
//      // Check if messageId is filled in to check if NumberLookup is created successfully
//      assertNotNull(numberLookup.getMessageId());
//
//      // Check if number is filled in
//      assertNotNull(numberLookup.getNumber());
//    }
//  }
//
//  /**
//   * This method checks if the found NumberLookup is equal to the expected one
//   *
//   * @param expected expected result
//   * @param actual actual result
//   */
//  private void checkNumberLookup(NumberLookup expected, NumberLookup actual) {
//    assertEquals(expected.getMessageId(), actual.getMessageId());
//    assertEquals(expected.getCreatedDateTime(), actual.getCreatedDateTime());
//    assertEquals(expected.getNumber(), actual.getNumber());
//    assertEquals(expected.getTag(), actual.getTag());
//  }
//}