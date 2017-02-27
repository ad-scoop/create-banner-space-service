package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.entites.TargetGroups;
import com.adscoop.bannerspace.entites.WebSiteNode;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import ratpack.handling.Context;
import ratpack.handling.Handler;

import java.util.Optional;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by kleistit on 23/02/2017.
 */
public class AddTargetGroupHandler implements Handler {

    private WebsiteNodeService websiteNodeService;

    @Inject
    public AddTargetGroupHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
String hostname = ctx.getPathTokens().get("hostname");

        if (!token.isEmpty() && !hostname.isEmpty()) {
            Optional<WebSiteNode> webSiteNode = websiteNodeService.findWebSiteByUserTokenAndHostname(token,hostname);
            if (webSiteNode.isPresent()) {
                ctx.parse(fromJson(TargetGroups.class)).then(targetGroups -> {

                    TargetGroups targetGroups1 = new TargetGroups();
                    targetGroups1.setFrom(targetGroups.getFrom());
                    targetGroups1.setGender(targetGroups.getGender());
                    targetGroups1.setTo(targetGroups.getTo());


                    targetGroups.getLabels().stream().filter(String::isEmpty).forEach(s -> {

                        targetGroups1.getLabels().add(s);

                    });

                    webSiteNode.get().addTargetGroup(targetGroups1);
                    ctx.render(json(targetGroups1));

                });


            } else {
                ctx.render(json("NO WEBSITE FOUND"));
            }


        } else {


        }
    }
}
