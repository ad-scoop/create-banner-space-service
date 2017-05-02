package com.adscoop.website.handlers;

import com.adscoop.website.entites.Region;

import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;
import ratpack.handling.Context;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;


/**
 * Created by thokle on 29/04/2017.
 */
public class WebSiteSearchByRegionHandler extends AbstractTokenHandler {

    private WebsiteService websiteService;

    @Inject
    public WebSiteSearchByRegionHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    protected void handleWithToken(Context ctx, String token) {
        ctx.parse(fromJson(Region.class)).then(s -> {
            websiteService.findWebSiteByRegion(s).then(   webSites -> ctx.render(json(webSites)));
        });
    }


}
