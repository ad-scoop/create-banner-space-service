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
       RxRatpack.observe(searchService.
               searchByArea(SearchParams.builder().
                       category(ctx.getPathTokens().getOrDefault("category", " *")).
                       zip(ctx.getPathTokens().getOrDefault("zip","")).
                       country(ctx.getPathTokens().getOrDefault("country","")).
                       region(ctx.getPathTokens().getOrDefault("region","")).
                       type(ctx.getPathTokens().getOrDefault("type", "* ")).
                       visitors(ctx.getPathTokens().getOrDefault("vistors", " *")).
                       gender(ctx.getPathTokens().getOrDefault("gender", "*")).
                       mixAge(Integer.valueOf(ctx.getPathTokens().getOrDefault("minAge", ""))).
                       maxAge(Integer.valueOf(ctx.getPathTokens().getOrDefault("maxAge", ""))).
                       physicalShop(Boolean.valueOf(ctx.getPathTokens().getOrDefault("physicalShop",""))).
                       build()))
       			.forEach( webSites -> ctx.render(json(webSites))  );

    }
}
