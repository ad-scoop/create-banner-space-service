package com.adscoop.website.handlers;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;

import ratpack.handling.Context;

public class WebSiteSearchByUrlHandler extends AbstractTokenHandler {

	private WebsiteService websiteService;

	@Inject
	public WebSiteSearchByUrlHandler(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	@Override
	protected void handleWithToken(Context ctx, String token) {
		ctx.parse(fromJson(SearchParams.class)).then(searchParams -> {
			websiteService.findByHostName(searchParams.getName()).then(webSites -> ctx.render(json(webSites)));
		});
	}

}
