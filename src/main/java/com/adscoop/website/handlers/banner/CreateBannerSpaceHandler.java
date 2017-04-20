package com.adscoop.website.handlers.banner;

import com.adscoop.website.entites.BannerSpace;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.fromJson;


/**
 * Created by thokle on 15/04/2017.
 */
public class CreateBannerSpaceHandler implements Handler {


    private WebsiteService websiteService;


    @Inject
    public CreateBannerSpaceHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String hostname = ctx.getPathTokens().get("hostname");
        String token = ctx.getRequest().getHeaders().get("token");


        if(ctx.getRequest().getMethod().isPost() && ctx.getRequest().getHeaders().get("token") !=null)

        websiteService.findWebSiteByUserTokenAndHostname(token,hostname).then(webSiteNode -> {

            ctx.parse(fromJson(BannerSpace.class)).then( bannerSpace -> {


                    webSiteNode.addBannerSpace(bannerSpace);
                websiteService.save(webSiteNode);


            });





        });
    }
}
