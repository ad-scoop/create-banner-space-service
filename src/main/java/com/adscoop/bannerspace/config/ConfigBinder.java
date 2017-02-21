package com.adscoop.bannerspace.config;

import com.adscoop.bannerspace.exceptions.BannerServiceClientException;
import com.adscoop.bannerspace.exceptions.BannerServiceServerException;
import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.handlers.website.GetWebsiteHandler;
import com.adscoop.bannerspace.services.BannerNodeService;
import com.adscoop.bannerspace.services.BannerNodeServiceImpl;
import com.adscoop.bannerspace.services.UserService;
import com.adscoop.bannerspace.services.UserServiceImpl;
import com.google.inject.AbstractModule;

/**
 * Created by thokle on 01/10/2016.
 */
public class ConfigBinder extends AbstractModule {
    @Override
    protected void configure() {



        bind(CreateWebSiteHandler.class).asEagerSingleton();
        bind(GetWebsiteHandler.class).asEagerSingleton();
        bind(CreateBannerHandler.class);

        //exceptions

        bind(BannerServiceClientException.class).asEagerSingleton();
        bind(BannerServiceServerException.class).asEagerSingleton();

        bind(ReserveBannerSpaceHandler.class).asEagerSingleton();
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();
        bind(BannerNodeService.class).to(BannerNodeServiceImpl.class).asEagerSingleton();

    }
}
