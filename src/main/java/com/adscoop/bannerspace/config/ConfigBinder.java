package com.adscoop.bannerspace.config;


import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.exceptions.BannerServiceClientException;
import com.adscoop.bannerspace.exceptions.BannerServiceServerException;
import com.adscoop.bannerspace.handlers.banner.CreateBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.bannerspace.AddBannerSpaceToWebsite;
import com.adscoop.bannerspace.handlers.website.AddRegionHandler;
import com.adscoop.bannerspace.handlers.website.AddTargetGroupHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.handlers.website.GetWebsiteHandler;
import com.adscoop.bannerspace.services.*;
import com.google.inject.AbstractModule;

/**
 * Created by thokle on 01/10/2016.
 */
public class ConfigBinder extends AbstractModule {
    @Override
    protected void configure() {

bind(AddRegionHandler.class).asEagerSingleton();
bind(AddTargetGroupHandler.class).asEagerSingleton();
        bind(AddBannerSpaceToWebsite.class).asEagerSingleton();

        bind(CreateWebSiteHandler.class).asEagerSingleton();

        bind(WebSiteChainAction.class).asEagerSingleton();
bind(AddBannerSpaceToWebsite.class).asEagerSingleton();
        bind(CreateBannerSpaceHandler.class).asEagerSingleton();
        bind(GetWebsiteHandler.class).asEagerSingleton();
        //exceptions

        bind(BannerServiceClientException.class).asEagerSingleton();
        bind(BannerServiceServerException.class).asEagerSingleton();

        bind(ReserveBannerSpaceHandler.class).asEagerSingleton();
        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();
        bind(BannerNodeService.class).to(BannerNodeServiceImpl.class).asEagerSingleton();

        bind(WebsiteNodeService.class).to(WebsiteNodeServiceImpl.class).asEagerSingleton();
    }

}
