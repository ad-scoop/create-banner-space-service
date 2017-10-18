package com.adscoop.website.chains;

import com.adscoop.website.handlers.Const;
import com.adscoop.website.handlers.CreateWebSiteHandler;
import com.adscoop.website.handlers.DeleteWebSiteHandler;
import com.adscoop.website.handlers.GetWebSitesHandler;
import com.adscoop.website.handlers.GetWebsiteHandler;
import com.adscoop.website.handlers.UpdateWebSisteHandler;
import com.adscoop.website.handlers.WebSiteSearchHandler;

import ratpack.func.Action;
import ratpack.handling.Chain;

public class WebSiteChainAction implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("create", CreateWebSiteHandler.class)
    		.put("update",   UpdateWebSisteHandler.class)
    		.get("search",   WebSiteSearchHandler.class)
    		.get(GetWebSitesHandler.class)
        	.get("/:" + Const.Parameter.ID, GetWebsiteHandler.class)
        	.delete("remove/:" + Const.Parameter.ID, DeleteWebSiteHandler.class);

    }
    
}
