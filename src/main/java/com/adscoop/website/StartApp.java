package com.adscoop.website;

import com.adscoop.website.chains.BannerSpaceChainAction;
import com.adscoop.website.chains.WebSiteChainAction;
import com.adscoop.website.config.ConfigBinder;
import com.adscoop.website.handlers.CORSHandler;
import com.adscoop.website.modules.Config;
import com.adscoop.website.modules.ServiceCommonConfigModule;

import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.BaseDir;
import ratpack.server.RatpackServer;

/**
 * Created by thokle on 16/10/2016.
 */
public class StartApp {

    public static void main(String... args) throws Exception {
        RxRatpack.initialize();

        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec
                .serverConfig(sfb -> sfb.baseDir(BaseDir.find())
                        .props("ratpack.properties")
                        .yaml("datasource.yaml")
                        .require("/db", Config.class)
                        .env().development(true)
                        .sysProps()
                        .build())
                .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ConfigBinder.class).module(ServiceCommonConfigModule.class)))
                .handlers(chain -> chain
                        .all(CORSHandler.class)
                        .prefix("website", WebSiteChainAction.class).prefix("website", BannerSpaceChainAction.class)));

    }


}


