import com.twizo.Twizo;
import com.twizo.TwizoFactory;
import com.twizo.controllers.widget.WidgetSessionType;
import com.twizo.dataaccess.Node;
import com.twizo.exceptions.TwizoException;
import com.twizo.models.NumberLookup;
import com.twizo.models.Sms;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class Example {

  private static Twizo twizo;

  private static NumberLookupExample numberLookupExample;

  private static BalanceExample balanceExample;

  private static SmsExample smsExample;

  private static VerificationExample verificationExample;

  private static WidgetExample widgetExample;

  private static BackupExample backupExample;

  private static BufferedReader input;

  public static void main(String[] args) {
    String apiKey = null;
    Node apiNode = null;

    Properties properties = new Properties();
    input = new BufferedReader(new InputStreamReader(System.in));

    try {
      properties.load(new FileInputStream("examples/example.properties"));

      // Check if properties file has values
      if (properties.getProperty("apiKey") != null && properties.getProperty("apiNode") != null) {
        apiKey = properties.getProperty("apiKey");
        apiNode = Node.valueOf(properties.getProperty("apiNode"));
      } else {
        System.out.println("Please enter your apiKey");
        apiKey = input.readLine();

        System.out.println("Please enter 0 for Asian node, 1 for European node");
        switch (Integer.parseInt(input.readLine())) {
          case 0:
            apiNode = Node.ASIA;
            break;
          case 1:
            apiNode = Node.EUROPE;
            break;
        }

        // Write properties to file
        OutputStream outputStream = new FileOutputStream("examples/example.properties");

        properties.setProperty("apiKey", apiKey);
        properties.setProperty("apiNode", apiNode.toString());
        properties.store(outputStream, null);
        outputStream.close();
      }
    } catch (IOException e) {
      Logger.getLogger(NumberLookupExample.class.getName()).log(Level.SEVERE, e.getMessage(), e);
    }

    // Create new Twizo instance
    try {
      twizo = new TwizoFactory(apiKey, apiNode);
    } catch (TwizoException e) {
      Logger.getLogger(NumberLookupExample.class.getName()).log(Level.SEVERE, e.getMessage(), e);
    }

    numberLookupExample = new NumberLookupExample(twizo);
    balanceExample = new BalanceExample(twizo);
    smsExample = new SmsExample(twizo);
    verificationExample = new VerificationExample(twizo);
    widgetExample = new WidgetExample(twizo);
    backupExample = new BackupExample(twizo);

    showMainMenu();
  }

  private static void showMainMenu() {
    boolean running = true;
    while (running) {
      System.out.println("Enter 1 for CreditBalance");
      System.out.println("Enter 2 for NumberLookup");
      System.out.println("Enter 3 for Sms");
      System.out.println("Enter 4 for 2FA");
      System.out.println("Enter 5 for Widget");
      System.out.println("Enter 6 for Backup codes");
      System.out.println("Enter 7 to stop");

      try {
        switch (Integer.parseInt(input.readLine())) {
          case 1:
            creditBalanceMenu();
            break;
          case 2:
            numberLookupMenu();
            break;
          case 3:
            smsMenu();
            break;
          case 4:
            twoFAMenu();
            break;
          case 5:
            widgetMenu();
            break;
          case 6:
            backupCodeMenu();
            break;
          case 7:
            running = false;
          default:
            break;
        }
      } catch (IOException e) {
        Logger.getLogger(Example.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      }
    }
  }

  /**
   * Execute getCreditBalance method
   */
  private static void creditBalanceMenu() {
    getCreditBalance();
  }

  /**
   * Show options for NumberLookup
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void numberLookupMenu() throws IOException {
    System.out.println("Enter 1 for creating a NumberLookup");
    System.out.println("Enter 2 for creating a simple NumberLookup");
    System.out.println("Enter 3 for getting the status of a NumberLookup");
    System.out.println("Enter 4 for polling for results");

    switch (Integer.parseInt(input.readLine())) {
      case 1:
        createNumberLookup();
        break;
      case 2:
        createSimpleNumberLookup();
        break;
      case 3:
        getNumberLookup();
        break;
      case 4:
        getResults();
        break;
      default:
        break;
    }
  }

  /**
   * Show options for Sms
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void smsMenu() throws IOException {
    System.out.println("Enter 1 for creating a Sms");
    System.out.println("Enter 2 for sending a simple Sms");
    System.out.println("Enter 3 for getting the status of a Sms");
    System.out.println("Enter 4 for polling for Delivery reports");

    switch (Integer.parseInt(input.readLine())) {
      case 1:
        sendSms();
        break;
      case 2:
        sendSimpleSms();
        break;
      case 3:
        getSms();
        break;
      case 4:
        getDeliveryReports();
        break;
      default:
        break;
    }
  }

  /**
   * Show options for 2fa
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void twoFAMenu() throws IOException {
    System.out.println("Enter 1 for creating a new verification");
    System.out.println("Enter 2 for verifying a received token");
    System.out.println("Enter 3 to get the status of a created verification");

    switch (Integer.parseInt(input.readLine())) {
      case 1:
        createVerification();
        break;
      case 2:
        verifyToken();
        break;
      case 3:
        getVerificationStatus();
        break;
      default:
        break;
    }
  }

  /**
   * Show options for WidgetSession
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void widgetMenu() throws IOException {
    System.out
        .println("Enter 1 for creating a new widgetSession");
    System.out
        .println("Enter 2 for getting the status of a session");

    switch (Integer.parseInt(input.readLine())) {
      case 1:
        createWidgetSession();
        break;
      case 2:
        getSessionStatus();
        break;
      default:
        break;
    }
  }

  /**
   * Show options for backup codes
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void backupCodeMenu() throws IOException {
    System.out.println("Enter 1 for creating new backup codes");
    System.out.println("Enter 2 to verify a backup code");
    System.out.println("Enter 3 to get the remaining backup codes");
    System.out.println("Enter 4 to update backup codes");
    System.out.println("Enter 5 to delete backup codes");

    switch (Integer.parseInt(input.readLine())) {
      case 1:
        createBackupCodes();
        break;
      case 2:
        verifyBackupCode();
        break;
      case 3:
        getRemainingBackupCodes();
        break;
      case 4:
        updateBackupCodes();
        break;
      case 5:
        deleteBackupCodes();
        break;
      default:
        break;
    }
  }

  /**
   * Execute getting creditBalance example
   */
  private static void getCreditBalance() {
    try {
      // Execute getCreditBalance example method and print result
      System.out.printf("%nResult: %s%n%n", balanceExample.getCreditBalanceExample());
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute creating a new NumberLookup example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void createNumberLookup() throws IOException {
    System.out.println("Please enter a internationally formatted phone number to lookup");
    try {
      // Execute createNumberLookup example method
      NumberLookup[] numberLookups = numberLookupExample.createNumberLookup(input.readLine());

      // Print results
      for (NumberLookup numberLookup : numberLookups) {
        System.out.printf("%nResult: %s%n%n", numberLookup);
      }
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  private static void createSimpleNumberLookup() throws IOException {
    System.out.println("Please enter a internationally formatted phone number to lookup");
    try {
      // Execute createSimpleNumberLookup example method and print the result
      System.out.printf("%nResult: %s%n%n",
          numberLookupExample.createSimpleNumberLookup(input.readLine()));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute getting status example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void getNumberLookup() throws IOException {
    System.out.println("Please enter a messageId of a previously created NumberLookup");
    try {
      // Execute getNumberLookup example method and print the result
      System.out.printf("%nResult: %s%n%n", numberLookupExample.getNumberLookup(input.readLine()));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute polling example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void getResults() throws IOException {
    try {
      // Execute getResults example method
      NumberLookup[] results = numberLookupExample.getResults();

      // Print results
      for (NumberLookup numberLookup : results) {
        System.out.printf("%nResult: %s%n%n", numberLookup);
      }
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute sendSms example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void sendSms() throws IOException {
    System.out.println("Please enter a recipient for the sms");
    try {
      // Execute sendSms example method
      Sms[] sms = smsExample.sendSms(input.readLine());

      // Print results
      for (Sms s : sms) {
        System.out.printf("%nResult: %s%n%n", s);
      }
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  private static void sendSimpleSms() throws IOException {
    System.out.println("Please enter a recipient for the sms");
    try {
      // Execute sendSimpleSms example method and print the result
      System.out.printf("%nResult: %s%n%n", smsExample.sendSimpleSms(input.readLine()));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute getSms example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void getSms() throws IOException {
    System.out.println("Please enter a messageId of an existing sms to get it's status");
    try {
      // Execute getSms example method and print the result
      System.out.printf("%nResult: %s%n%n", smsExample.getStatus(input.readLine()));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute getDeliveryReports example
   */
  private static void getDeliveryReports() {
    try {
      // Execute getDeliveryReports example method
      Sms[] deliveryReports = smsExample.getDeliveryReports();

      // Print result
      for (Sms sms : deliveryReports) {
        System.out.printf("%nResult: %s%n%n", sms);
      }
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute createVerification example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void createVerification() throws IOException {
    try {
      System.out.println("Please enter a phone number for 2fa");

      // Read the recipient's phone number from the console
      String phoneNumber = input.readLine();

      // Execute createVerificationExample and print the result
      System.out.printf("%nResult: %s%n%n", verificationExample.createVerification(phoneNumber));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute verifyToken example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void verifyToken() throws IOException {
    try {
      System.out.println("Please enter the messageId");

      // Read messageId from the console
      String messageId = input.readLine();

      System.out.println("Please enter the token");

      // Read token from the console
      String token = input.readLine();

      // Verify the token by using the token and messageId and print the result
      System.out.printf("%nResult: %s%n%n", verificationExample.verifyToken(messageId, token));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute getVerificationStatus example
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void getVerificationStatus() throws IOException {
    try {
      System.out.println("Please enter a messageId");

      // Read messageId from the console
      String messageId = input.readLine();

      // Get verification status and print the result
      System.out.printf("%nResult: %s%n%n", verificationExample.getVerificationStatus(messageId));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Local enum to define the type of widgetSession example method
   */
  private enum SessionMethodType {
    CREATE, STATUS
  }

  /**
   * Execute createWidgetSession examples
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void createWidgetSession() throws IOException {
    executeWidgetMethod(null, SessionMethodType.CREATE);
  }

  /**
   * Execute getSessionStatus examples
   *
   * @throws IOException when something goes wrong during reading the console
   */
  private static void getSessionStatus() throws IOException {
    System.out.println("Please enter the sessionToken");

    // Read the sessionToken from the console
    String sessionToken = input.readLine();

    executeWidgetMethod(sessionToken, SessionMethodType.STATUS);
  }

  /**
   * Execute the right widget method
   *
   * @param sessionToken unique identifier of a session
   * @param sessionMethodType type of method to be executed
   * @throws IOException when something goes wrong during reading the console
   */
  private static void executeWidgetMethod(String sessionToken, SessionMethodType sessionMethodType)
      throws IOException {
    WidgetSessionType type = null;
    String recipient = null;
    String identifier = null;

    System.out.println("Enter sms/call, backupcode or both to define the allowed type");
    switch (input.readLine()) {
      case "sms/call":
        type = WidgetSessionType.RECIPIENT;

        System.out.println("Please enter a recipient");

        // Read the recipient from the console
        recipient = input.readLine();
        break;
      case "backupcode":
        type = WidgetSessionType.BACKUPCODE;

        System.out.println("Please enter a backupCode identifier");

        // Read the backupCode identifier from the console
        identifier = input.readLine();
        break;
      case "both":
        type = WidgetSessionType.BOTH;

        System.out.println("Please enter a recipient");

        // Read the recipient from the console
        recipient = input.readLine();

        System.out.println("Please enter a backupCode identifier");

        // Read the backupCode identifier from the console
        identifier = input.readLine();
    }

    // Execute the right widgetSession example method base on the sessionMethodType
    try {
      switch (sessionMethodType) {
        case CREATE:

          System.out.println("Enter object or json to define the resultType");
          switch (input.readLine()) {
            case "object":
              // Execute the method and print the result
              System.out.printf("%nResult: %s%n%n",
                  widgetExample.createWidgetSession(recipient, identifier, type));
              break;
            case "json":
              // Execute the method and print the result
              System.out.printf("%nResult: %s%n%n",
                  widgetExample.createWidgetSessionJson(recipient, identifier, type));
              break;
          }
          break;
        case STATUS:

          System.out.println("Enter object or json to define the resultType");
          switch (input.readLine()) {
            case "object":
              // Execute the method and print the result
              System.out.printf("%nResult: %s%n%n",
                  widgetExample.getSessionStatus(sessionToken, recipient, identifier, type));
              break;
            case "json":
              // Execute the method and print the result
              System.out.printf("%nResult: %s%n%n",
                  widgetExample.getSessionStatusJson(sessionToken, recipient, identifier, type));
              break;
          }
          break;
        default:
          break;
      }
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute createBackupCodes example
   */
  private static void createBackupCodes() {
    try {
      // Execute createBackupCodes example and print the result
      System.out.printf("%nResult: %s%n%n", backupExample.createBackupCodes());
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute verifyBackupCode example
   *
   * @throws IOException when something goes wrong during the reading of the console
   */
  private static void verifyBackupCode() throws IOException {
    System.out.println("Please enter a backup code to verify");

    try {
      // Execute verifyBackupCode example and print the result
      System.out.printf("%nResult: %s%n%n", backupExample.verifyBackupCode(input.readLine()));
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute getRemainingBackupCodes example
   */
  private static void getRemainingBackupCodes() {
    try {
      // Execute getRemainingCodes example and print the result
      System.out.printf("%nResult: %s%n%n", backupExample.getRemainingCodes());
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute updateBackupCodes example
   */
  private static void updateBackupCodes() {
    try {
      // Execute updateBackupCodes example and print the result
      System.out.printf("%nResult: %s%n%n", backupExample.updateBackupCodes());
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }

  /**
   * Execute deleteBackupCodes example
   */
  private static void deleteBackupCodes() {
    try {
      // Execute deleteBackupCodes example
      backupExample.deleteBackupCodes();
    } catch (TwizoException e) {
      // Make sure to process the Exception data
      Logger.getLogger(SmsExample.class.getName()).log(Level.SEVERE, e.getMessage());
    }
  }
}