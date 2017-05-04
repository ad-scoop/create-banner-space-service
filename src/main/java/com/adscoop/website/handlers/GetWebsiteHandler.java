package com.adscoop.website.handlers;

import static ratpack.jackson.Jackson.json;

import org.apache.commons.lang3.StringUtils;

import com.adscoop.website.services.WebsiteService;
import com.google.inject.Inject;

import ratpack.handling.Context;
import ratpack.http.Status;

public class GetWebsiteHandler extends AbstractTokenHandler {

	WebsiteService websiteService;

	@Inject
	public GetWebsiteHandler(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	@Override
	protected void handleWithToken(Context ctx, String token) {
		String id = ctx.getPathTokens().get(Const.Parameter.ID);
		if (StringUtils.isEmpty(id)) {
			ctx.getResponse().status(Status.of(412));
			ctx.render("Website missing");
		} else {
			websiteService.findById(Long.parseLong(id)).then(w -> {
				if (w == null) {
					ctx.getResponse().status(Status.of(404));
					ctx.render("Website not found");
				} else {
					ctx.render(json(w));
				}
			});
		}
	}

}
