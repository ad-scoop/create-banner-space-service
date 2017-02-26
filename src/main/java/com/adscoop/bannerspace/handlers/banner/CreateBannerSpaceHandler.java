package com.adscoop.bannerspace.handlers.banner;


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
 * Created by thokle on 17/10/2016.
 */
public class CreateBannerSpaceHandler implements Handler {

    private WebsiteNodeService websiteNodeService;

    @Inject
    public CreateBannerSpaceHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

        if (ctx.getRequest().getHeaders().get("token") != null) {

            String token = ctx.getRequest().getHeaders().get("token");
            ctx.parse(fromJson(BannerSpace.class)).then(bannerSpace -> {


                Optional<WebSiteNode> webSiteNode = websiteNodeService.findWebSiteByUserToken(token);


                BannerSpace bannerSpace1 = new BannerSpace();
                bannerSpace1.setLattiude(bannerSpace.getLattiude());

                bannerSpace.getLabels().stream().filter(labelfil -> labelfil != null).forEach(la -> {

                    bannerSpace1.getLabels().add(la);
                });


                bannerSpace1.setDomain(bannerSpace.getDomain());
                bannerSpace1.setLongitude(bannerSpace.getLongitude());
                bannerSpace1.setLattiude(bannerSpace.getLattiude());
                bannerSpace1.setPositionSiteL(bannerSpace.getPositionSiteL());
                bannerSpace1.setPositionSiteM(bannerSpace.getPositionSiteM());
                bannerSpace1.setPrice(bannerSpace.getPrice());


                webSiteNode.get().addBannerSpace(bannerSpace1);


                ctx.render(json(bannerSpace1));


                websiteNodeService.save(webSiteNode.get());
            });


        }

    }
}
