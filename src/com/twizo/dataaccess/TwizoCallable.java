package com.twizo.dataaccess;

import com.twizo.exceptions.TwizoCallException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.concurrent.Callable;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class TwizoCallable implements Callable<String> {

    /**
     * Default username to connect to Twizo's servers
     */
    private static final String USERNAME = "twizo";

    /**
     * Connection which is required to interact with Twizo's servers
     */
    private HttpsURLConnection connection;

    /**
     * Type of request which will be executed
     */
    private final RequestType requestType;

    /**
     * Parameters in JSON format which can be added to the request
     */
    private final String params;

    public TwizoCallable(String host, String apiKey, String functionUrl, String params,
                         RequestType requestType) throws TwizoCallException {
        this.requestType = requestType;
        this.params = params;

        try {
            URL url = new URL(String.format("%s/%s", host, functionUrl));
            connection = (HttpsURLConnection) url.openConnection();

            String authentication = Base64.getEncoder()
                    .encodeToString(String.format("%s:%s", USERNAME, apiKey).getBytes("UTF-8"));
            connection.setRequestProperty("Authorization", "Basic " + authentication);

            connection.setDoInput(true);
            connection.setRequestProperty("Accept", "application/json");
            connection.setUseCaches(false);
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("User-Agent", String
                    .format("%s/v%s/%s/%s", "lib-api-java", "0.1.0", "Java",
                            System.getProperty("java.version")));
        } catch (IOException e) {
            throw new TwizoCallException(e);
        }
    }

    /**
     * Execute the specific Callable method
     *
     * @return String in JSON format with information gathered from the API.
     */
    @Override
    public String call() throws TwizoCallException {
        try {
            switch (requestType) {
                case GET:
                    return sendGet();
                case POST:
                    return sendPost();
                case PUT:
                    return sendPut();
                case DELETE:
                    sendDelete();
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            throw new TwizoCallException(e);
        }

        return null;
    }

    /**
     * Send a GET request to the Twizo Servers
     *
     * @return JSON String with information gathered from the server
     * @throws IOException when something went wrong with the connection or InputStream
     */
    private String sendGet() throws IOException, TwizoCallException {
        connection.setRequestMethod(requestType.toString());

        switch (connection.getResponseCode()) {
            case 200:
                return processOutput(false);
            case 422:
                return processOutput(true);
            default:
                throw new TwizoCallException(String.format("The server responded with status code: %d",
                        connection.getResponseCode()));
        }
    }

    /**
     * Send a POST request to the Twizo servers
     *
     * @return JSON String with information gathered from the server
     * @throws IOException when something went wrong during the API call
     */
    private String sendPost() throws IOException, TwizoCallException {
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        if (this.params != null) {
            setParameters(connection.getOutputStream());

            if (connection.getResponseCode() == 201) {
                return processOutput(false);
            } else {
                throw new TwizoCallException(String.format("The server responded with code: %d%nMessage:%n %s",
                        connection.getResponseCode(), processOutput(true)));
            }
        } else {
            throw new TwizoCallException("Parameters cannot be NULL");
        }
    }

    /**
     * Send a PUT request to the Twizo servers
     *
     * @return JSON String with information gathered from the server
     * @throws ProtocolException when the PUT method cannot be found
     */
    private String sendPut() throws IOException, TwizoCallException {
        connection.setRequestMethod("PUT");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        if (this.params != null) {
            setParameters(connection.getOutputStream());
        }

        if (connection.getResponseCode() == 200) {
            return processOutput(false);
        } else {
            throw new TwizoCallException(String.format("The server responded with code: %d%nMessage:%n %s",
                    connection.getResponseCode(), processOutput(true)));
        }
    }

    /**
     * Send a DELETE request to the Twizo servers
     *
     * @throws ProtocolException when the DELETE method cannot be found
     */
    private void sendDelete() throws IOException, TwizoCallException {
        connection.setRequestMethod("DELETE");
        connection.disconnect();

        if (connection.getResponseCode() != 204) {

            throw new TwizoCallException(String.format("The server responded with code: %d%nMessage:%n %s",
                    connection.getResponseCode(), processOutput(true)));
        }
    }

    /**
     * Add parameters to a request
     *
     * @param outputStream outputStream of the connection
     * @throws IOException when something goes wrong during writing to the outputStream
     */
    private void setParameters(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(params);
        dataOutputStream.flush();
        dataOutputStream.close();
    }

    /**
     * Process output from Twizo server response
     *
     * @return Processed output in String format
     * @throws IOException When something went wrong during reading the connection
     */
    private String processOutput(boolean exception) throws IOException {
        InputStream inputStream;

        if (exception) {
            inputStream = connection.getErrorStream();
        } else {
            inputStream = connection.getInputStream();
        }

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();

        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        bufferedReader.close();
        connection.disconnect();

        return stringBuilder.toString();
    }
}