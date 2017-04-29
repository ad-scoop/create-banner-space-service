package com.adscoop.website.handlers;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.adscoop.website.services.WebsiteService;

import ratpack.handling.Context;
import ratpack.http.Status;

public class DeleteWebSiteHandler extends AbstractTokenHandler {

	private WebsiteService websiteService;

	@Inject
	public DeleteWebSiteHandler(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}

	@Override
	protected void handleWithToken(Context ctx, String token) {
		String host = ctx.getPathTokens().get(Const.Parameter.HOST);
		if (StringUtils.isEmpty(host)) {
			ctx.getResponse().status(Status.of(412));
			ctx.render("Website not deleted missing host");
		} else {
			this.websiteService.delete(host);
			ctx.render("Website deleted");
		}
	}
	
}
