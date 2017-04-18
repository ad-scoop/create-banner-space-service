package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.entites.WebSite;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

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
            if(ctx.getRequest().getMethod().isPut()){
        String token = ctx.getRequest().getHeaders().get("token");
        String hostname = ctx.getPathTokens().get("hostname");
        if (!token.isEmpty() && !hostname.isEmpty()) {
            ctx.parse(fromJson(WebSite.class)).then(w -> RxRatpack.observe(websiteNodeService.findWebSiteByUserTokenAndHostname(token, hostname)).forEach(webSiteNode -> {
                webSiteNode.setHostname(w.getHostname());
                webSiteNode.setPath(w.getPath());
                webSiteNode.setPort(w.getPort());
                websiteNodeService.save(webSiteNode);
                ctx.render(json(webSiteNode));
            }));
        }
        } else
            {
                ctx.next();
            }
    }
}
