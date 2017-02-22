package com.adscoop.bannerspace.handlers.banner;

import ratpack.handling.Context;
import ratpack.handling.Handler;

/**
 * Created by thokle on 19/11/2016.
 */
public class ReserveBannerSpaceHandler implements Handler{




    @Override
    public void handle(Context ctx) throws Exception {

        String userToken = ctx.getPathTokens().get("userBannerToken");
        String bannerid = ctx.getPathTokens().get("bannerid");

        if(!userToken.isEmpty()&&!bannerid.isEmpty()){





        }

    }
}
