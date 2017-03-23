package com.adscoop.bannerspace.config;


import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.exceptions.BannerServiceClientException;
import com.adscoop.bannerspace.exceptions.BannerServiceServerException;
import com.adscoop.bannerspace.handlers.CORSHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.handlers.website.GetWebsiteHandler;
import com.adscoop.bannerspace.services.*;
import com.google.inject.AbstractModule;
import io.netty.handler.codec.http.cors.CorsHandler;

/**
 * Created by thokle on 01/10/2016.
 */
public class ConfigBinder extends AbstractModule {
    @Override
    protected void configure() {


        bind(CreateWebSiteHandler.class).asEagerSingleton();

        bind(WebSiteChainAction.class).asEagerSingleton();

        bind(GetWebsiteHandler.class).asEagerSingleton();
        //exceptions

        bind(BannerServiceClientException.class).asEagerSingleton();
        bind(BannerServiceServerException.class).asEagerSingleton();

        bind(UserService.class).to(UserServiceImpl.class).asEagerSingleton();

        bind(WebsiteNodeService.class).to(WebsiteNodeServiceImpl.class).asEagerSingleton();
        bind(JsonUtil.class).asEagerSingleton();
        bind(CORSHandler.class).asEagerSingleton();
    }

}
