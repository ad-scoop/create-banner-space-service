package com.adscoop.bannerspace;

import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.config.ConfigBinder;
import com.adscoop.bannerspace.handlers.CORSHandler;
import com.adscoop.bannerspace.modules.Config;
import com.adscoop.bannerspace.modules.ServiceCommonConfigModule;

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
                        .prefix("website", WebSiteChainAction.class)));

    }


}


