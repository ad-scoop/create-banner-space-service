package com.adscoop.website.handlers.website;

import com.adscoop.website.services.WebsiteService;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

import javax.inject.Inject;



/**
 * Created by thokle on 15/04/2017.
 */
public class DeleteWebSiteHandler implements Handler{

    private WebsiteService websiteService;


    @Inject
    public DeleteWebSiteHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String token = ctx.getRequest().getHeaders().get("token");
        String hostname = ctx.getPathTokens().get("hostname");

        if(ctx.getRequest().getMethod().isDelete()) {
            if (!token.isEmpty() && !hostname.isEmpty()) {
                websiteService.findWebSiteByUserTokenAndHostname(token, hostname).then(webSite -> {
                    if(webSite !=null) {
                        websiteService.delete(webSite);
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
