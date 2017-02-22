package com.adscoop.bannerspace.config;

import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.handlers.website.GetWebsiteHandler;
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
        bind(WebSiteChainAction.class).asEagerSingleton();

    }

}
