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
		String id = ctx.getPathTokens().get(Const.Parameter.ID);
		if (StringUtils.isEmpty(id)) {
			ctx.getResponse().status(Status.of(412));
			ctx.render("Website not deleted missing url");
		} else {
			this.websiteService.delete(Long.parseLong(id));
			ctx.render("Website deleted");
		}
	}
	
}
