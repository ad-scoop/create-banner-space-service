package com.adscoop.website.handlers;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import javax.inject.Inject;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;

import ratpack.handling.Context;

public class UpdateWebSisteHandler extends AbstractTokenHandler {

	private WebsiteService websiteService;

	@Inject
	public UpdateWebSisteHandler(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	@Override
	protected void handleWithToken(Context ctx, String token) {
		ctx.parse(fromJson(WebSite.class)).then(w -> {
			w.setUserToken(token);
			this.websiteService.save(w);
			ctx.render(json("update ok"));
		});
	}
	
}
