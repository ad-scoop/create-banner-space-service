package com.adscoop.bannerspace.handlers.website;

import static ratpack.jackson.Jackson.json;

import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

/**
 * Created by thokle on 17/11/2016.
 */
public class GetWebsiteHandler implements Handler {

    WebsiteNodeService websiteNodeService;

    @Inject
    public GetWebsiteHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String usertoken = ctx.getRequest().getHeaders().get("token");
        String hostname = ctx.getPathTokens().get("hostname");
                if(ctx.getRequest().getMethod().isGet() && !hostname.isEmpty()){
                    if(!usertoken.isEmpty()){
                      RxRatpack.observe(websiteNodeService.findWebSiteByUserTokenAndHostname(usertoken,hostname)).forEach(webSiteNodes -> {
                          ctx.render(json(webSiteNodes));
                      });
                    }

                } else if(hostname.isEmpty()){

                } else {

                }

    }
}
