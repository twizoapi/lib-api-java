package com.twizo.services.sms;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.twizo.exceptions.SmsException;
import com.twizo.exceptions.TwizoJsonParseException;
import com.twizo.models.Sms;
import com.twizo.services.AbstractService;
import javafx.util.Pair;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class JsonSmsService extends AbstractService implements SmsService {

    /**
     * Parse the data which is returned after sending a SMS to a Sms object array
     *
     * @param data data which has to be parsed
     * @return Sms object with server data inside
     * @throws SmsException            when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during json parsing
     */
    @Override
    public Sms[] parseNewSms(String data) throws TwizoJsonParseException, SmsException {
        if (data != null) {
            try {
                return parseToSms(data, "items", "total_items", false).getValue();
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new SmsException("Twizo didn't response as expected, please try again");
    }

    /**
     * Parse the data which is returned after getting the status of a sms to a Sms object
     *
     * @param data data which has to be parsed
     * @return Sms object with server data inside
     * @throws SmsException            when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during json parsing
     */
    @Override
    public Sms parseSms(String data) throws TwizoJsonParseException, SmsException {
        if (data != null) {
            try {
                return gson.fromJson(data, Sms.class);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new SmsException("Twizo didn't response as expected, please try again");
    }

    /**
     * Parse delivery report data to an array of Sms objects
     *
     * @param data data which has to parsed
     * @return Pair consisting batchId and array of Sms instances
     * @throws SmsException            when something goes wrong during the process
     * @throws TwizoJsonParseException when something goes wrong during json parsing
     */
    @Override
    public Pair<String, Sms[]> parseDeliveryReports(String data) throws SmsException, TwizoJsonParseException {
        if (data != null) {
            try {
                return parseToSms(data, "messages", "count", true);
            } catch (JsonSyntaxException ex) {
                throw new TwizoJsonParseException(ex);
            }
        }
        throw new SmsException("Twizo didn't response as expected, please try again");
    }

    /**
     * Parse Json Sms data to a Sms object
     *
     * @param json           Json which will be parsed
     * @param memberName     member of the json to use (items or messages)
     * @param counterName    name of counter in json
     * @param deliveryReport boolean to decide whether to get the batchId or not
     * @return Pair of batchId and array of NumberLookup objects
     */
    private Pair<String, Sms[]> parseToSms(String json, String memberName, String counterName, boolean deliveryReport)
            throws JsonSyntaxException {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        String batchId = null;

        if (deliveryReport) {
            // Get the batchId
            batchId = jsonObject.get("batchId").getAsString();
        }
        Sms[] smsArray = new Sms[jsonObject.get(counterName).getAsInt()];

        if (jsonObject.get(counterName).getAsInt() > 0) {
            JsonArray itemArray = jsonObject.getAsJsonObject("_embedded").getAsJsonArray(memberName);

            for (int i = 0; i < smsArray.length; i++) {
                String items = itemArray.get(i).getAsJsonObject().toString();
                smsArray[i] = gson.fromJson(items, Sms.class);
            }
        }

        return new Pair<>(batchId, smsArray);
    }
}
