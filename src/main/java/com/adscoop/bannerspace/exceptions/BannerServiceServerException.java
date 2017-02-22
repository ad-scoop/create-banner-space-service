package com.adscoop.bannerspace.exceptions;

import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 05/02/2017.
 */
public class BannerServiceServerException implements ServerErrorHandler {
    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        context.getResponse().status(500).send("text",throwable.getMessage());
    }
}
