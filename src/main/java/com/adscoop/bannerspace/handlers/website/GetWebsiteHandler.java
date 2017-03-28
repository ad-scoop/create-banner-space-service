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
                if(ctx.getRequest().getMethod().isGet()){
                    if(!usertoken.isEmpty()){
                      RxRatpack.observe(websiteNodeService.findWebSitesByToken(usertoken)).forEach(webSiteNodes -> {
                          ctx.render(json(webSiteNodes));
                      });
                    }

                }

    }
}
