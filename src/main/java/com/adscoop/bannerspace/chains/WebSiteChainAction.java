package com.adscoop.bannerspace.chains;

import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.handlers.website.GetWebsiteHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 16/01/2017.
 */
public class WebSiteChainAction implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post(CreateWebSiteHandler.class).get(GetWebsiteHandler.class);


    }
}
