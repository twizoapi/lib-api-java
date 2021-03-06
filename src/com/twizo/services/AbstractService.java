package com.twizo.services;

import com.google.gson.Gson;

/**
 * This file is part of the Twizo Java API
 *
 * For the full copyright and licence information, please view the Licence file that was distributed
 * with this source code
 *
 * (c) Twizo - info@twizo.com
 */
public abstract class AbstractService {

    protected final Gson gson;

    public AbstractService() {
        this.gson = new Gson();
    }

}
