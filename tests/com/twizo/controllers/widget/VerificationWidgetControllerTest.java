package com.twizo.controllers.widget;

import static org.junit.Assert.*;

import com.twizo.Twizo;
import com.twizo.TwizoFactory;
import com.twizo.controllers.TestSetup;
import com.twizo.controllers.backup.BackupController;
import com.twizo.dataaccess.Node;
import com.twizo.dataaccess.jsonparams.WidgetSessionParams;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.BackupCode;
import com.twizo.models.WidgetSession;
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
public class VerificationWidgetControllerTest extends TestSetup {

  private ApiVerificationWidgetController apiVerificationWidgetController;

  private BackupController backupController;

  private WidgetSessionParams params;

  private final String phoneNumber = "60123400000";

  @Before
  public void setUp() throws TwizoException {
    apiVerificationWidgetController = new ApiVerificationWidgetController(NODE, TESTAPIKEY);

    Twizo twizo = new TwizoFactory(TESTAPIKEY, Node.ASIA);
    backupController = twizo.getBackupController();

    params = new WidgetSessionParams();
  }

  /**
   * This method tests the creation of a session with sms/call as allowedType
   */
  @Test
  public void createSessionRecipientTest() {
    params.setAllowedTypes(new String[]{"sms", "call"});
    params.setRecipient(phoneNumber);

    try {
      WidgetSession session = apiVerificationWidgetController.createWidgetSession(params);

      // Check if session is created successfully
      assertNotNull(session);

      // Check if result contains sessionToken
      assertNotNull(session.getSessionToken());

      // Check if result recipient is equal to phoneNumber
      assertEquals(phoneNumber, session.getRecipient());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests the creation of a session with backup code as allowedType
   */
  @Test
  public void createSessionBackupTest() {
    try {
      backupController.deleteBackupCodes(phoneNumber);
      executeCreateSessionBackupTest(backupController.createBackupCodes(phoneNumber));
    } catch (TwizoException e) {
      try {
        executeCreateSessionBackupTest(backupController.createBackupCodes(phoneNumber));
      } catch (TwizoException e1) {
        e1.printStackTrace();
        fail();
      }
    }

  }

  /**
   * Execute createSessionBackupTest
   *
   * @param backupCode backupCode to add to the request
   */
  private void executeCreateSessionBackupTest(BackupCode backupCode) {
    params.setAllowedTypes(new String[]{"backupcode"});
    params.setBackupCodeIdentifier(backupCode.getIdentifier());

    try {
      WidgetSession widgetSession = apiVerificationWidgetController.createWidgetSession(params);

      // Check if session is created successfully
      assertNotNull(widgetSession);

      // Check if result contains sessionToken
      assertNotNull(widgetSession.getSessionToken());

      // Check if result recipient is equal to phoneNumber
      assertEquals(backupCode.getIdentifier(), widgetSession.getBackupCodeIdentifier());
    } catch (TwizoException e) {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * This method tests if an exceptions gets thrown when mandatory parameter allowedType is null
   *
   * @throws TwizoException because mandatory parameters is null
   */
  @Test(expected = TwizoException.class)
  public void nullAllowedTypeTest() throws TwizoException {
    params.setAllowedTypes(null);
    apiVerificationWidgetController.createWidgetSession(params);
  }

  /**
   * This method tests the case where tokenLength is set, but tokenType is not
   *
   * @throws TwizoException because both parameters above have to be set if 1 is not null
   */
  @Test(expected = TwizoException.class)
  public void nullTokenTypeTest() throws TwizoException {
    params.setTokenLength(8);
    apiVerificationWidgetController.createWidgetSession(params);
  }

  /**
   * This method tests the case where tokenType is set, but tokenLength is not
   *
   * @throws TwizoException because both parameters above have to be set if 1 is not null
   */
  @Test(expected = TwizoException.class)
  public void nullTokenLengthTest() throws TwizoException {
    params.setTokenType("numeric");
    apiVerificationWidgetController.createWidgetSession(params);
  }
}