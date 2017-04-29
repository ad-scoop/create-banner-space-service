package com.adscoop.website.chains;

import com.adscoop.website.handlers.Const;
import com.adscoop.website.handlers.CreateWebSiteHandler;
import com.adscoop.website.handlers.DeleteWebSiteHandler;
import com.adscoop.website.handlers.GetWebSitesHandler;
import com.adscoop.website.handlers.GetWebsiteHandler;
import com.adscoop.website.handlers.UpdateWebSisteHandler;

import ratpack.func.Action;
import ratpack.handling.Chain;

public class WebSiteChainAction implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("create",CreateWebSiteHandler.class)
    		.post("update", UpdateWebSisteHandler.class)
    		.get(GetWebSitesHandler.class)
        	.get("/:" + Const.Parameter.URL,GetWebsiteHandler.class)
        	.delete("remove/:" + Const.Parameter.URL, DeleteWebSiteHandler.class);
    }
    
}
