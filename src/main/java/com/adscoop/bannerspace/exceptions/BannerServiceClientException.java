package com.adscoop.bannerspace.exceptions;

import ratpack.error.ClientErrorHandler;
import ratpack.func.Action;
import ratpack.handling.Context;

/**
 * Created by thokle on 05/02/2017.
 */
public class BannerServiceClientException implements Action<Throwable> {

    @Override
    public void execute(Throwable throwable) throws Exception {

    }
}
