package com.adscoop.website.handlers;


import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.SearchService;
import ratpack.handling.Context;
import ratpack.rx.RxRatpack;
import javax.inject.Inject;
import static ratpack.jackson.Jackson.json;

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
       String zip =  ctx.getPathTokens().getOrDefault("zip","2000");
       String country= ctx.getPathTokens().getOrDefault("country","dk");
       String region = ctx.getPathTokens().getOrDefault("region","copenhagen");
       String type = ctx.getPathTokens().getOrDefault("type", "* ");
       String category = ctx.getPathTokens().getOrDefault("category", " *");
       String visitors = ctx.getPathTokens().getOrDefault("vistors", " *");
       String gender = ctx.getPathTokens().getOrDefault("gender", "*");
       int mixAge = Integer.valueOf(ctx.getPathTokens().getOrDefault("minAge", "0"));
       int maxAge = Integer.valueOf(ctx.getPathTokens().getOrDefault("maxAge", String.valueOf(100)));
       boolean physicalShop = Boolean.valueOf(ctx.getPathTokens().getOrDefault("physicalShop","false"));

       RxRatpack.observe(searchService.
               searchByArea(SearchParams.builder().
                       category(category).
                       zip(zip).country(country).
                       region(region).
                       type(type).
                       visitors(visitors).
                       gender(gender).
                       mixAge(mixAge).
                       maxAge(maxAge).
                       physicalShop(physicalShop).build())).forEach( webSites -> ctx.render(json(webSites))  );

    }
}
