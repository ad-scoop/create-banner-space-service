package com.adscoop.bannerspace.chains;

import com.adscoop.bannerspace.handlers.banner.CreateBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.banner.GetBannerSpacesHandler;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 19/01/2017.
 */
public class BannerChainAction implements Action<Chain> {


    @Override
    public void execute(Chain chain) throws Exception {
        chain.post(CreateBannerSpaceHandler.class).post("reserve/:userBannerToken/:bannerid", ReserveBannerSpaceHandler.class).get(GetBannerSpacesHandler.class);
    }
}
