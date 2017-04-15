package com.adscoop.bannerspace.handlers.banner;

import com.adscoop.bannerspace.entites.BannerSpace;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.fromJson;


/**
 * Created by thokle on 15/04/2017.
 */
public class CreateBannerSpaceHandler implements Handler {


    private WebsiteNodeService websiteNodeService;


    @Inject
    public CreateBannerSpaceHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String hostname = ctx.getPathTokens().get("hostname");
        String token = ctx.getRequest().getHeaders().get("token");


        if(ctx.getRequest().getMethod().isPost() && ctx.getRequest().getHeaders().get("token") !=null)

        websiteNodeService.findWebSiteByUserTokenAndHostname(token,hostname).then( webSiteNode -> {

            ctx.parse(fromJson(BannerSpace.class)).then( bannerSpace -> {


                    webSiteNode.addBannerSpace(bannerSpace);
                websiteNodeService.save(webSiteNode);


            });





        });
    }
}
