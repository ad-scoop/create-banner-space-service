package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.nodeServices.WebsiteNodeService;
import com.google.inject.Inject;
import jdk.nashorn.internal.ir.annotations.Immutable;
import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 17/11/2016.
 */
public class GetWebsiteHandler implements Handler {

WebsiteNodeService     websiteNodeService;

    @Inject
    public GetWebsiteHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

    }
}
