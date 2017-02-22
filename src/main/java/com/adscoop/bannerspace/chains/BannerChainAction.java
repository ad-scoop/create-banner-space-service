package com.adscoop.bannerspace.chains;

import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.banner.GetBannerSpaces;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import ratpack.func.Action;
import ratpack.handling.Chain;

/**
 * Created by thokle on 19/01/2017.
 */
public class BannerChainAction implements Action<Chain> {


    @Override
    public void execute(Chain chain) throws Exception {
        chain.post(CreateBannerHandler.class).post("reserve/:userBannerToken/:bannerid", ReserveBannerSpaceHandler.class).get(GetBannerSpaces.class);
    }
}
