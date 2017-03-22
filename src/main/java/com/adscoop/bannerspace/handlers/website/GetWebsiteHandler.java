package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.config.JsonUtil;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.google.inject.Inject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.rx.RxRatpack;

import java.nio.charset.Charset;

/**
 * Created by thokle on 17/11/2016.
 */
public class GetWebsiteHandler implements Handler {

    WebsiteNodeService websiteNodeService;

    @Inject
    public GetWebsiteHandler(WebsiteNodeService websiteNodeService) {
        this.websiteNodeService = websiteNodeService;
    }

    @Override
    public void handle(Context ctx) throws Exception {
        String usertoken = ctx.getRequest().getHeaders().get("token");
                if(ctx.getRequest().getMethod().isGet()){
                    if(!usertoken.isEmpty()){
                      RxRatpack.observe(websiteNodeService.findWebSiteByUserTokenAndHostname(usertoken,ctx.getPathTokens().get("hostname"))).subscribe( webSiteNode -> {
                          String s = JsonUtil.toString(webSiteNode);
                          ByteBuf ByteBuf = Unpooled.copiedBuffer(s, Charset.defaultCharset());
                         ctx.getResponse().send("application/json", ByteBuf);
                      });
                    }

                }

    }
}
