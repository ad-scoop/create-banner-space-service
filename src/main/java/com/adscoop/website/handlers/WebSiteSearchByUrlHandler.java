package com.adscoop.website.handlers;

import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import ratpack.handling.Context;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;
import static ratpack.jackson.Jackson.jsonNode;


/**
 * Created by thokle on 29/04/2017.
 */
public class
WebSiteSearchByUrlHandler extends AbstractTokenHandler {

    private WebsiteService websiteService;
private StringUtils stringUtils;
    @Inject
    public WebSiteSearchByUrlHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    protected void handleWithToken(Context ctx, String token) {


                ctx.parse(fromJson(SearchParams.class)).then( searchParams -> {
                    websiteService.findByHostName(searchParams.getName()).then( webSites ->  ctx.render(json(webSites)));
                });


    }


}
