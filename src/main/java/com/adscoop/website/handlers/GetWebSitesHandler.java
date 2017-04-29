package com.adscoop.website.handlers;

import static ratpack.jackson.Jackson.json;
import static ratpack.rx.RxRatpack.observeEach;

import javax.inject.Inject;

import com.adscoop.website.services.WebsiteService;

import ratpack.handling.Context;

public class GetWebSitesHandler extends AbstractTokenHandler {

    private WebsiteService websiteService;

    @Inject
    public GetWebSitesHandler(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

	@Override
	protected void handleWithToken(Context ctx, String token) {
		observeEach(this.websiteService
				.findByToken(token))
				.toList()
				.forEach(w -> ctx.render(json(w)));
	}
	
}
