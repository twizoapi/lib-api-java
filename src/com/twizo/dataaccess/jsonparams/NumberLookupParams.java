package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class NumberLookupParams {

    /**
     * This is a mandatory array with string parameter. It should be an array of numbers (in string
     * format), in international format, for the number lookup. At least 1 number must be set and
     * maximum 1000 numbers can be set.
     */
    private String[] numbers;

    /**
     * This is an optional string parameter. The tag is a free text parameter you can use for your own
     * reference. The maximum length of the tag is 30 characters. The tag parameter is returned in the
     * result and you can use it for reporting purposes on your side.
     */
    private String tag;

    /**
     * This is an optional integer parameter. The validity specifies how long the Number Lookup is
     * valid. The validity is in seconds. If the Number Lookup could not be performed within this
     * time, the Number Lookup is expired. The minimum value of the validity is 5 seconds and the
     * maximum value is 259200 seconds (= 72 hours). The default value is 259200 seconds.
     */
    private Integer validity;

    /**
     * This is an optional integer parameter. If you want to receive or poll for final results of your
     * verifications, you can set the resultType. You can use the results for your own reporting
     * purposes. Possible values of the resultType are:
     *
     * 0:	No results (default)
     * 1:	Callback (you have to specify the callbackUrl)
     * 2:	Polling
     * 3:	Callback & polling (you have to specify the callbackUrl)
     */
    private Integer resultType;

    /**
     * This string parameter is only mandatory when resultType is set to 1 or 3. When the callbackUrl
     * is set, this URL will be used by our system to submit status updates to you. This parameter is
     * only allowed when the resultType parameter is set to 1 or 3.
     */
    private String callbackUrl;

    public String[] getNumbers() {
        return numbers;
    }

    public void setNumbers(String[] numbers) {
        this.numbers = numbers;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
