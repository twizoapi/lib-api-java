//package com.twizo.controllers.application;
//
//import static org.mockito.Mockito.*;
//import static org.junit.Assert.*;
//
//import com.twizo.dataaccess.RequestType;
//import com.twizo.dataaccess.Worker;
//import com.twizo.exceptions.ApplicationException;
//import com.twizo.exceptions.TwizoCallException;
//import com.twizo.exceptions.TwizoJsonParseException;
//import com.twizo.models.Credentials;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * This file is part of the Twizo Java API
// *
// * For the full copyright and licence information, please view the Licence file that was distributed with this source code
// *
// * (c) Twizo - info@twizo.com
// */
//public class ApiApplicationControllerTest {
//
//    private ApplicationController applicationController;
//
//    private Worker worker;
//
//    @Before
//    public void setUp() throws Exception {
//        worker = mock(Worker.class);
//    }
//
//    //TODO onderstaande verplaatsen naar unit test voor balance package
////    /**
////     * Tests getCreditBalance method
////     */
////    @Test
////    public void getCreditbalanceTest() {
////        String response = "{\n" +
////                "    \"credit\": 4.912,\n" +
////                "    \"currencyCode\": \"eur\",\n" +
////                "    \"freeVerifications\": 100,\n" +
////                "    \"wallet\": \"Default wallet\"\n" +
////                "}";
////
////        Balance result = null;
////
////        // Mock getBalance API response
////        try {
////            when(worker.execute("wallet/getbalance", null, RequestType.GET)).thenReturn(response);
////            applicationController = new ApiApplicationController(worker);
////
////            result = applicationController.getCreditBalance();
////        } catch (TwizoCallException | ApplicationException | TwizoJsonParseException e) {
////            e.printStackTrace();
////        }
////
////        // Check credit
////        assertEquals(4.912, result.getCredit(), 0.0001);
////
////        // Check amount of free verifications
////        assertEquals(100, result.getFreeVerifications().intValue());
////    }
////
////    /**
////     * Tests getCreditBalance method when the server returns an invalid JSON string
////     *
////     * @throws TwizoJsonParseException because returned JSON is invalid
////     */
////    @Test(expected = TwizoJsonParseException.class)
////    public void getCreditbalanceInvalidJsonTest() throws TwizoJsonParseException {
////        String invalidResponse = "\"credit\": 4.912,\n" +
////                "    \"currencyCode\": \"eur\",\n" +
////                "    \"freeVerifications\": 100,\n" +
////                "    \"wallet\": \"Default wallet\"\n";
////
////        try {
////            when(worker.execute("wallet/getbalance", null, RequestType.GET)).thenReturn(invalidResponse);
////            applicationController = new ApiApplicationController(worker);
////
////            // TwizoJsonParseException will be thrown because of invalid JSON
////            applicationController.getCreditBalance();
////        } catch (TwizoCallException | ApplicationException e) {
////            e.printStackTrace();
////        }
////    }
//
//    /**
//     * Tests verifying valid credentials
//     */
//    @Test
//    public void verifyValidCredentialsTest() {
//        String response = "{\n" +
//                "    \"applicationTag\": \"Test application\",\n" +
//                "    \"isTestKey\": true\n" +
//                "}";
//
//        Credentials credentials = null;
//
//        try {
//            when(worker.execute("application/verifycredentials", null, RequestType.GET)).
//                    thenReturn(response);
//            applicationController = new ApiApplicationController(worker);
//
//            credentials = applicationController.verifyCredentials();
//        } catch (TwizoCallException | ApplicationException | TwizoJsonParseException e) {
//            e.printStackTrace();
//        }
//
//        // Check application tag
//        assertEquals("Test application", credentials.getApplicationTag());
//
//        // Check testKey value
//        assertTrue(credentials.isTestKey());
//    }
//
//    /**
//     * Tests verifyCredentials method when the server returns an invalid JSON string
//     *
//     * @throws TwizoJsonParseException because returned JSON is invalid
//     */
//    @Test(expected = TwizoJsonParseException.class)
//    public void verifyCredentialsInvalidJsonTest() throws TwizoJsonParseException {
//        String invalidJson = "{\n" +
//                "    \"applicationTag\": \"Test application\"\n" +
//                "    \"isTestKey\": true\n" +
//                "}";
//
//        try {
//            when(worker.execute("application/verifycredentials", null, RequestType.GET)).
//                    thenReturn(invalidJson);
//
//            applicationController = new ApiApplicationController(worker);
//
//            applicationController.verifyCredentials();
//        } catch (TwizoCallException | ApplicationException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    /**
//     * Tests verifying invalid credentials
//     *
//     * @throws TwizoJsonParseException when something goes wrong during JSON parsing, should not be thrown
//     * @throws TwizoCallException      when something goes wrong during API calling
//     * @throws ApplicationException    expected because credentials are invalid
//     */
//    @Test(expected = ApplicationException.class)
//    public void verifyInvalidCredentialsTest() throws TwizoJsonParseException, TwizoCallException, ApplicationException {
//        when(worker.execute("application/verifycredentials", null, RequestType.GET)).
//                thenThrow(new TwizoCallException("Invalid"));
//        applicationController = new ApiApplicationController(worker);
//
//        // Verify invalid credentials
//        applicationController.verifyCredentials();
//    }
//
//    /**
//     * Tests getAllowedTypes method
//     */
//    @Test
//    public void getAllowedTypesTest() {
//        String response = "{\n" +
//                "    \"0\": \"sms\",\n" +
//                "    \"1\": \"call\",\n" +
//                "    \"2\": \"backupcode\"\n" +
//                "}";
//
//        try {
//            when(worker.execute("application/verification_types", null, RequestType.GET)).
//                    thenReturn(response);
//
//            applicationController = new ApiApplicationController(worker);
//
//            // Execute method
//            String[] types = applicationController.getAllowedTypes();
//
//            // Check if number of allowed types is equal to 3
//            assertEquals(3, types.length);
//
//            // Check if JSON gets parsed correctly
//            assertEquals("sms", types[0]);
//            assertEquals("call", types[1]);
//            assertEquals("backupcode", types[2]);
//        } catch (TwizoCallException | ApplicationException | TwizoJsonParseException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * Tests getAllowedTypes method when the server returns an invalid JSON string
//     *
//     * @throws TwizoJsonParseException because returned JSON is invalid
//     */
//    @Test(expected = TwizoJsonParseException.class)
//    public void getAllowedTypesInvalidJsonTest() throws TwizoJsonParseException {
//        String invalidResponse = "{\n" +
//                "    \"0\": \"sms\",\n" +
//                "    \"1\": \"call\",\n" +
//                "    \"2\": \"backupcode\"\n";
//
//
//        try {
//            when(worker.execute("application/verification_types", null, RequestType.GET)).
//                    thenReturn(invalidResponse);
//
//            applicationController = new ApiApplicationController(worker);
//
//            // Execute method
//            applicationController.getAllowedTypes();
//        } catch (TwizoCallException | ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
//
//}