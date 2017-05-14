package com.adscoop.website.handlers;


import static ratpack.jackson.Jackson.fromJson;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.http.Status;

public class CreateWebSiteHandler extends AbstractTokenHandler {

    private WebsiteService websiteService;
    
    @Inject
    public CreateWebSiteHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

	@Override
	protected void handleWithToken(Context ctx, final String token) {
        ctx.parse(fromJson(WebSite.class)).then(webSite -> {
            this.websiteService.urlNameExists(webSite.getUrl()).then(exists -> {
            		if (exists) {
            			ctx.getResponse().status(Status.of(409));
            			ctx.render("Web site exist");
            		} else {
            			webSite.setToken(token);

            			this.websiteService.save(webSite);
            			ctx.render("Web site created");
            		}
            	});
        });
	}
	
}
