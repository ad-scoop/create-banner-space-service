package com.adscoop.bannerspace.handlers.bannerspace;

import com.adscoop.bannerspace.entites.BannerSpace;
import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 23/02/2017.
 */
public class AddBannerSpaceToWebsite implements Handler {

   private
   WebsiteNodeService websiteNodeService;

            @Inject
    public AddBannerSpaceToWebsite(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
                String token = ctx.getRequest().getHeaders().get("token");

                if(!token.isEmpty()){
                    ctx.parse(fromJson(BannerSpace.class)).then( bannerSpace -> {
                    Optional<WebSiteNode> webSiteNode = websiteNodeService.findWebSiteByUserToken(token);
                    if(webSiteNode.isPresent()){
                        webSiteNode.get().addBannerSpace(bannerSpace);

                        websiteNodeService.save(webSiteNode.get());
                        ctx.render(json("BannnerSpace has been added to website"));



                    }else {
                        ctx.render(json("WebSite does not exist on user"));

                    }
                    });
                }else {
                    ctx.render(json("token was empty"));
                }

    }
}
