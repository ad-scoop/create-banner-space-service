package com.adscoop.website.handlers.website;


import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.UserService;
import com.adscoop.website.services.WebsiteNodeService;
import com.google.inject.Inject;

import ratpack.exec.Promise;
import ratpack.handling.Context;
import ratpack.handling.Handler;

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
            ctx.parse(fromJson(WebSite.class)).then(webSiteNode -> {
                Promise<WebSite> hostname = websiteNodeService.findByHostName(webSiteNode.getHostname());

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
