package com.adscoop.website.chains;

import com.adscoop.website.handlers.*;

import ratpack.func.Action;
import ratpack.handling.Chain;

public class WebSiteChainAction implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("create",CreateWebSiteHandler.class)
                .post("search", WebSiteSearchByRegionHandler.class)
    		.post("update", UpdateWebSisteHandler.class)
    		.get(GetWebSitesHandler.class)
        	.get("/:" + Const.Parameter.URL,GetWebsiteHandler.class)
        	.delete("remove/:" + Const.Parameter.URL, DeleteWebSiteHandler.class);
    }
    
}
