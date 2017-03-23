package com.adscoop.bannerspace.handlers.website;


import com.adscoop.bannerspace.entites.UserNode;
import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.UserService;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 15/11/2016.
 */
public class CreateWebSiteHandler implements Handler {

    UserService userService;
    WebsiteNodeService websiteNodeService;
    @Inject
    public CreateWebSiteHandler(UserService userService, WebsiteNodeService websiteNodeService) {
        this.userService = userService;
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        if (ctx.getRequest().getMethod().isPost()) {
            ctx.parse(fromJson(WebSiteNode.class)).then(webSiteNode -> {
                Promise<WebSiteNode> hostname = websiteNodeService.findByHostName(webSiteNode.getHostname());

                hostname.then(webSiteNode1 -> {
                    if(webSiteNode1 == null) {
                        if (token != null) {
                            userService.findUserByToken(token).then(userNode -> {

                                userNode.addWebSite(webSiteNode);
                                userService.save(userNode);
                                ctx.render(json(webSiteNode));

                            });

                        } else {
                            ctx.next();
                        }
                    } else {
                        ctx.render("Web site exist");
                    }
                });

            });
        } else {
ctx.next();
        }
    }
}
