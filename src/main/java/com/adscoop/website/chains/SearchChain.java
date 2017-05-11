package com.adscoop.website.chains;

import com.adscoop.website.handlers.WebSiteSearchHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by kleistit on 11/05/2017.
 */
public class SearchChain implements Action<Chain> {


    @Override
    public void execute(Chain chain) throws Exception {
        chain.get(":zip?&:country?&region:", WebSiteSearchHandler.class);
    }
}
