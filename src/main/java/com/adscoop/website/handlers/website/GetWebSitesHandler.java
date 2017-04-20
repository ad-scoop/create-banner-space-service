package com.adscoop.website.handlers.website;

import com.adscoop.website.services.WebsiteService;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 17/04/2017.
 */
public class GetWebSitesHandler implements Handler {

    private WebsiteService websiteService;

    @Inject
    public GetWebSitesHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        if (ctx.getRequest().getMethod().isGet()) {
            if (!token.isEmpty()) {
                websiteService.findWebSitesByToken(token).then(webSites ->  {
                    ctx.render(json(webSites));
                });

            }
        } else {
            ctx.next();
        }

    }
}
