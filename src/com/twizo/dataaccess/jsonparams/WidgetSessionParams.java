package com.twizo.dataaccess.jsonparams;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class WidgetSessionParams {

  /**
   * This is a mandatory array parameter. The allowedTypes defines which verification types can be
   * used with the widget. Possible values are: ‘sms’, ‘call’ or ‘backupcode’. The default value is
   * [‘sms’, ‘call’]. The first type you set as allowed type will be the first verification the
   * widget will perform. The API will filter out certain types when that option is not available
   * for the user. For example when you set ‘backupcode’ but for the backupCodeIdentifier you
   * specified the API cannot find backup codes, the type will be removed. See ‘requestedTypes’ and
   * ‘allowedTypes’ in the status result for more information.
   */
  private String[] allowedTypes;

  /**
   * This is a mandatory string parameter when for the allowedTypes ‘sms’ or ‘call’ is included. The
   * recipient is a single phone number in international format. The phone number can be a mobile
   * number or a fixed phone number.
   */
  private String recipient;

  /**
   * This is a mandatory string parameter when for the allowedTypes ‘backupcode’ is included. The
   * backupCodeIdentifier is the identifier you used for creating the backup codes for the user.
   */
  private String backupCodeIdentifier;

  /**
   * This is an optional integer parameter. The tokenLength defines the length of the token. The
   * minimum value is 4 and the maximum value 10. The default value is 6. When you set the
   * tokenLength, you have to set the tokenType as well.
   */
  private Integer tokenLength;

  /**
   * This is an optional string parameter. Possible values are ‘numeric’ and ‘alphanumeric’. Numeric
   * token will only contain the digits 0-9. For alphanumeric the token contains the digits 0-9 and
   * characters a-z (lowercase). The default value of the tokenType is ‘numeric’. When you set the
   * tokenType, you have to set the tokenLength as well.
   */
  private String tokenType;

  /**
   * This is an optional string parameter. The tag is a free text parameter you can use for your own
   * reference. The maximum length of the tag is 30 characters. The tag parameter is returned in the
   * result and you can use it for reporting purposes on your side.
   */
  private String tag;

  public String[] getAllowedTypes() {
    return allowedTypes;
  }

  public void setAllowedTypes(String[] allowedTypes) {
    this.allowedTypes = allowedTypes;
  }

  public String getRecipient() {
    return recipient;
  }

  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  public String getBackupCodeIdentifier() {
    return backupCodeIdentifier;
  }

  public void setBackupCodeIdentifier(String backupCodeIdentifier) {
    this.backupCodeIdentifier = backupCodeIdentifier;
  }

  public Integer getTokenLength() {
    return tokenLength;
  }

  public void setTokenLength(Integer tokenLength) {
    this.tokenLength = tokenLength;
  }

  public String getTokenType() {
    return tokenType;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
}
