package com.adscoop.website.handlers;

import static ratpack.jackson.Jackson.json;

import org.apache.commons.lang3.StringUtils;

import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.http.Status;

public abstract class AbstractTokenHandler implements Handler {

	@Override
	public void handle(Context ctx) {
		String token = ctx.getRequest().getHeaders().get(Const.Headers.TOKEN);
		if (StringUtils.isEmpty(token)) {
			ctx.getResponse().status(Status.of(406));
			ctx.render(json("no token"));
		} else {
			this.handleWithToken(ctx, token);
		}
	}

	protected abstract void handleWithToken(Context ctx, String token);

}
