package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.entites.Regions;
import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by kleistit on 26/02/2017.
 */
public class AddRegionHandler implements Handler {

    private WebsiteNodeService websiteNodeService;

    @Inject
    public AddRegionHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");

        if (!token.isEmpty()) {

            Optional<WebSiteNode> webSiteNode = websiteNodeService.findWebSiteByUserToken(token);
            if (webSiteNode.isPresent()) {
                ctx.parse(fromJson(Regions.class)).then(regions -> {
                    Regions regions1 = new Regions();

                    regions1.setCountry(regions.getCountry());
                    regions1.setLattiude(regions.getLattiude());
                    regions1.setLogitude(regions.getLogitude());
                    regions1.setRegionname(regions.getRegionname());


                    webSiteNode.get().addRegion(regions1);
                    websiteNodeService.save(webSiteNode.get());


                });


            } else {
                ctx.render(json("NO WEBSITE FOUND"));

            }


        } else {
            ctx.render(json("NO TOKEN PRESENT"));

        }

    }
}
