package com.adscoop.website.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ratpack.error.ServerErrorHandler;
import ratpack.handling.Context;

/**
 * Created by thokle on 05/02/2017.
 */
public class BannerServiceServerException implements ServerErrorHandler {
    private  static Logger logger = LoggerFactory.getLogger(BannerServiceServerException.class);
    @Override
    public void error(Context context, Throwable throwable) throws Exception {
        logger.debug("Error has occured = "+throwable.getMessage());
        context.getResponse().status(500).send("text", throwable.getMessage());
        throwable.printStackTrace();
    }
}
