![Twizo](https://www.twizo.com/wp-content/themes/twizo/_/images/twizo-logo-0474ce6f.png) 


# Twizo Java API #

Connect to the Twizo API using Java. This API includes functions to send verifications (2FA), SMS and Number Lookup.

## Requirements ##
* Java >= 8
* Maven

## Get application secret and api host ##
To use the Twizo API client, the following things are required:

* Create a [Twizo account](https://register.twizo.com/)
* Login on the Twizo portal
* Find your [application](https://portal.twizo.com/applications/) secret
* Find your nearest api [node](https://www.twizo.com/developers/documentation/#introduction_api-url)

## Installation ##

At this moment there are 2 options to use the Twizo Java library (IntelliJ)
#### Maven ####
1. Add Maven Framework to your project by right clicking the project folder -> Add Framework Support -> Maven
2. Add the lib-api-java module module to your project
3. Add the module as a dependency to your pom.xml file

```xml
<dependency>
    <groupId>com.twizo</groupId>
    <artifactId>lib-api-java</artifactId>
    <version>0.1.0</version>
</dependency>
```
4. Make sure to mark the src folder as source directory to be able to use the Twizo classes
5. Reimport (or enable auto-import) Maven

#### Jar file ####
1. Put the lib-api-java.jar file in your lib folder
2. Add the library to the project in the Project Structure -> Libraries menu

In the near future this library will be available from the Maven Central Repository

## Getting started ##

Initializing the Twizo Api using your api secret and api host

```java
import com.twizo.TwizoFactory;
import com.twizo.Twizo;

class MyClass {

    private static void main(String[] args) {
        Twizo twizo = new TwizoFactory("your-api-key", Node.ASIA);
    }
    
}
```
Send a sms

```java
SmsController smsController = twizo.getSmsController();

// parameters = recipient phone number - message - sender phone number
smsController.sendSimple("60123456789", "Hello World", "60987654321");
```

Create a new NumberLookup

```java
NumberLookupController numberLookupController = twizo.getNumberLookupController();

// parameter = phone number to look up
numberLookupController.createSimpleNumberLookup("60123456789");
```

Create a new Verification

```java
VerificationController verificationController = twizo.getVerificationController();

// parameter = phone number to receive token
verificationController.createSimpleVerification("60123456789");
```

Verify a token

```java
VerificationController verificationController = twizo.getVerificationController();

// parameter = token to verify
verificationController.verifyToken("012345");
```

## Examples ##

In the examples directory you can find a collection of cli examples of how to use the api.
When first running an example you will be asked for a host name and apiKey; this will be written to a properties file.

## Testing ##
To run the Unit Tests provided with this code, you have to enter your API key in the TestSetup class in the tests directory.

## License ##
[The MIT License](https://opensource.org/licenses/mit-license.php).
Copyright (c) 2017 Twizo

## Support ##
Contact: [www.twizo.com](http://www.twizo.com/) — support@twizo.com
