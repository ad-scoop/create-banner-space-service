package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.services.WebsiteNodeService;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 17/04/2017.
 */
public class GetWebSitesHandler implements Handler {

    private WebsiteNodeService websiteNodeService;

    @Inject
    public GetWebSitesHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        if (ctx.getRequest().getMethod().isGet()) {
            if (!token.isEmpty()) {
                websiteNodeService.findWebSitesByToken(token).then( webSites ->  {
                    ctx.render(json(webSites));
                });

            }
        } else {
            ctx.next();
        }

    }
}
