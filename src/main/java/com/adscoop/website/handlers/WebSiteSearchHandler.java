package com.adscoop.website.handlers;

import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import java.util.List;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;


/**
 * Created by thokle on 29/04/2017.
 */
public class WebSiteSearchHandler extends AbstractTokenHandler {

    private WebsiteService websiteService;

    @Inject
    public WebSiteSearchHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    protected void handleWithToken(Context ctx, String token) {
        ctx.parse(fromJson(SearchParams.class)).then(s -> {
            websiteService.findByNames(s.getNames()).then(   webSites -> ctx.render(json(webSites)));
        });
    }


}
