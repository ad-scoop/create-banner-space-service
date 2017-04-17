package com.adscoop.bannerspace.config;


import com.adscoop.bannerspace.chains.BannerSpaceChainAction;
import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.exceptions.BannerServiceClientException;
import com.adscoop.bannerspace.exceptions.BannerServiceServerException;
import com.adscoop.bannerspace.handlers.CORSHandler;
import com.adscoop.bannerspace.handlers.website.*;
import com.adscoop.bannerspace.services.UserService;
import com.adscoop.bannerspace.services.UserServiceImpl;
import com.adscoop.bannerspace.services.WebsiteNodeService;
import com.adscoop.bannerspace.services.WebsiteNodeServiceImpl;
import com.google.inject.AbstractModule;

/**
 * Created by thokle on 01/10/2016.
 */
public class ConfigBinder extends AbstractModule {
    @Override
    protected void configure() {

        bind(CreateWebSiteHandler.class).asEagerSingleton();

        bind(WebSiteChainAction.class).asEagerSingleton();
        bind(BannerSpaceChainAction.class).asEagerSingleton();
        bind(GetWebsiteHandler.class).asEagerSingleton();
        bind(GetWebSitesHandler.class).asEagerSingleton();
        bind(DeleteWebSiteHandler.class).asEagerSingleton();
        bind(UpdateWebSisteHandler.class).asEagerSingleton();
        //exceptions

        bind(BannerServiceClientException.class).asEagerSingleton();
        bind(BannerServiceServerException.class).asEagerSingleton();

        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();

        bind(WebsiteNodeService.class).to(WebsiteNodeServiceImpl.class).asEagerSingleton();
        bind(JsonUtil.class).asEagerSingleton();
        bind(CORSHandler.class).asEagerSingleton();
    }

}
