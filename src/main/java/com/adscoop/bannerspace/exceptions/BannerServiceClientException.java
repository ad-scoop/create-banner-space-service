package com.adscoop.bannerspace.exceptions;

import ratpack.error.ClientErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 05/02/2017.
 */
public class BannerServiceClientException implements ClientErrorHandler {

    @Override
    public void error(Context context, int statusCode) throws Exception {
        context.file("404.html");
    }
}
