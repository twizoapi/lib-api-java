import com.twizo.Twizo;
import com.twizo.TwizoFactory;
import com.twizo.dataaccess.Node;
import com.twizo.exceptions.ApplicationException;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public abstract class AbstractExample {

    protected static Twizo twizo;

    protected static BufferedReader input;

    private static String apiKey;

    private static Node apiNode;

    private static Properties properties;

    /**
     * Get Twizo instance
     *
     * @return new Twizo instance
     */
    public static Twizo getTwizo() {
        input = new BufferedReader(new InputStreamReader(System.in));

        // Check if Twizo is initialized already
        if (twizo != null) {
            return twizo;
        }

        properties = new Properties();

        try {
            // Try to read credentials from properties file
            properties.load(new FileInputStream("examples/example.properties"));

            // Check if credentials are in file
            if (properties.getProperty("apiKey") != null && properties.getProperty("apiNode") != null) {
                apiKey = properties.getProperty("apiKey");
                apiNode = Node.valueOf(properties.getProperty("apiNode"));
            } else {
                // Read credentials from console
                readCredentials();
            }

            // Create and return new Twizo instance
            twizo = new TwizoFactory(apiKey, apiNode);
            return twizo;

        } catch (IOException | ApplicationException e) {
            Logger.getLogger(AbstractExample.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return null;
        }

    }

    /**
     * Read credentials from console
     *
     * @throws IOException when something goes wrong during reading the console
     */
    private static void readCredentials() throws IOException {
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

        OutputStream outputStream = new FileOutputStream("examples/example.properties");

        properties.setProperty("apiKey", apiKey);
        properties.setProperty("apiNode", apiNode.toString());
        properties.store(outputStream, null);
        outputStream.close();
    }
}
