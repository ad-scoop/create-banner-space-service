package com.adscoop.website.chains;

import com.adscoop.website.handlers.WebSiteSearchHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by kleistit on 11/05/2017.
 */
public class SearchChain implements Action<Chain> {


    /**    Map<String,Object> map = new HashMap();
     map.put("country", area.getCountry());
     map.put("zip",area.getZip());
     map.put("region",area.getRegion());
     map.put("category",area.getCategory());
     map.put("gender",area.getGender());
     map.put("minAge",area.getMixAge());
     map.put("maxAge",area.getMaxAge());
     map.put("type",area.getType());
     map.put("visitors",area.getVisitors());
     map.put("physicalShop",area.isPhysicalShop());
     */

    @Override
    public void execute(Chain chain) throws Exception {
        chain.get(":zip?/:country?/:region?/:category?/:gender?/:minAge?/:maxAge?/:type?/:visitors?/:physicalShop?", WebSiteSearchHandler.class);
    }
}
