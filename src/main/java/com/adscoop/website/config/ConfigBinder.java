package com.adscoop.website.config;


import com.adscoop.website.chains.BannerSpaceChainAction;
import com.adscoop.website.chains.WebSiteChainAction;
import com.adscoop.website.exceptions.BannerServiceClientException;
import com.adscoop.website.exceptions.BannerServiceServerException;
import com.adscoop.website.handlers.CORSHandler;
import com.adscoop.website.handlers.website.*;
import com.adscoop.website.services.UserService;
import com.adscoop.website.services.UserServiceImpl;
import com.adscoop.website.services.WebsiteService;
import com.adscoop.website.services.WebsiteServiceImpl;
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

        bind(WebsiteService.class).to(WebsiteServiceImpl.class).asEagerSingleton();
        bind(JsonUtil.class).asEagerSingleton();
        bind(CORSHandler.class).asEagerSingleton();
    }

}
