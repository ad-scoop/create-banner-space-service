package com.adscoop.website.chains;

import com.adscoop.website.handlers.website.*;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 16/01/2017.
 */
public class WebSiteChainAction implements Action<Chain> {
    @Override
    public void execute(Chain chain) throws Exception {
        chain.post("create",CreateWebSiteHandler.class)
        	.get("get/:hostname",GetWebsiteHandler.class)
        	.get("all", GetWebSitesHandler.class)
        	.put("update/:hostname", UpdateWebSisteHandler.class)
        	.delete("delete/:hostname", DeleteWebSiteHandler.class);


    }
}
