package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.sun.xml.internal.ws.encoding.ContentType;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 15/04/2017.
 */
public class DeleteWebSiteHandler implements Handler{

    private WebsiteNodeService websiteNodeService;


    @Inject
    public DeleteWebSiteHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        String hostname = ctx.getPathTokens().get("hostname");

        if(ctx.getRequest().getMethod().isDelete()) {
            if (!token.isEmpty() && !hostname.isEmpty()) {
                websiteNodeService.findWebSiteByUserTokenAndHostname(token, hostname).then(webSite -> {
                    if(webSite !=null) {
                        websiteNodeService.delete(webSite);
                        ctx.getResponse().status(Status.OK).send("application/json", "{ \"Hostname\" :" + hostname + " ,\"was deletede\" }");
                    } else {
                        ctx.render("Hostname "+hostname + "  does not exits");

                    }
                });

            } else {
                ctx.getResponse().send("Token and or Hostname are missing");

            }
        } else  {
            ctx.next();

        }


    }
}
