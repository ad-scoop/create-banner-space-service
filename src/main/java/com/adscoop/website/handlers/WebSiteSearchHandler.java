package com.adscoop.website.handlers;

import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.SearchService;
import org.apache.commons.lang3.StringUtils;
import ratpack.handling.Context;

import javax.inject.Inject;

/**
 * Created by thokle on 09/05/2017.
 */
public class WebSiteSearchHandler extends  AbstractTokenHandler{


    private SearchService searchService;


    @Inject
    public WebSiteSearchHandler(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    protected void handleWithToken(Context ctx, String token) {


       String zip =  ctx.getPathTokens().get("zip");
       String country= ctx.getPathTokens().get("country");
       String region = ctx.getPathTokens().get("region");
       String type = ctx.getPathTokens().getOrDefault("type", "* ");
       String category = ctx.getPathTokens().getOrDefault("category", " *");
       String visitors = ctx.getPathTokens().getOrDefault("vistors", " *");
       String gender = ctx.getPathTokens().getOrDefault("gender", "*");
       int mixAge = Integer.valueOf(ctx.getPathTokens().getOrDefault("minAge", "0"));
       int maxAge = Integer.valueOf(ctx.getPathTokens().getOrDefault("maxAge", String.valueOf(100)));
       boolean physicalShop = Boolean.valueOf(ctx.getPathTokens().getOrDefault("physicalShop","false"));

        SearchParams.builder().category(category).zip(zip).country(country).region(region).type(type).visitors(visitors).gender(gender).mixAge(mixAge).maxAge(maxAge).physicalShop(physicalShop).build();





    }
}
