package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
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
