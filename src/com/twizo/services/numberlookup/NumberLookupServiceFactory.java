package com.twizo.services.numberlookup;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public class NumberLookupServiceFactory {

  /**
   * Get a new instance of JsonNumberLookupService
   *
   * @return JsonNumberLookupService instance
   */
  public NumberLookupService getInstance() {
    return new JsonNumberLookupService();
  }
}
