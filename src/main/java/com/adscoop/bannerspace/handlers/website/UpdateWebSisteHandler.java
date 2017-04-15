package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 15/04/2017.
 */
public class UpdateWebSisteHandler implements Handler {

    private WebsiteNodeService websiteNodeService;

    @Inject
    public UpdateWebSisteHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {

        String token = ctx.getRequest().getHeaders().get("token");
        String hostname = ctx.getPathTokens().get("hostname");
        if (!token.isEmpty() && !hostname.isEmpty()) {
            ctx.parse(fromJson(WebSiteNode.class)).then(w -> {


                websiteNodeService.findWebSiteByUserTokenAndHostname(hostname, token).then(webSiteNode -> {
                     webSiteNode.setHostname(w.getHostname());
                     webSiteNode.setPath(w.getPath());
                     webSiteNode.setPort(w.getPort());

                     websiteNodeService.save(webSiteNode);

                     ctx.render(json(webSiteNode));
                });

            });
        }
    }
}
