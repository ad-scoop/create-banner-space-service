package com.adscoop.bannerspace.handlers.website;


import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.bannerspace.entites.Company;
import com.adscoop.bannerspace.entites.UserNode;
import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.CompanyService;
import com.adscoop.bannerspace.services.UserService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

/**
 * Created by thokle on 15/11/2016.
 */
public class CreateWebSiteHandler implements Handler {

    UserService userService;

    @Inject
    public CreateWebSiteHandler(UserService userService) {
        this.userService = userService;

    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        if(ctx.getRequest().getMethod().isPost()){
        ctx.parse(fromJson(WebSiteNode.class)).then(webSiteNode ->  {


            if(token != null){

                Optional<UserNode> userNode = userService.findUserByToken(token);
                if(userNode.isPresent()) {
                    WebSiteNode webSiteNode1 = new WebSiteNode();
                    webSiteNode1.setHostname(webSiteNode.getHostname());
                    webSiteNode1.setPath(webSiteNode.getPath());
                    webSiteNode1.setPort(webSiteNode.getPort());
                    userService.save(userNode.get());
                    ctx.render(json(webSiteNode1));
                }else {

                    ctx.render(json("User not found"));

                }

            }else{
                ctx.render(json("no token present in header"));
            }
        });
    } else {
            ctx.next();
        }

    }


}
