package com.adscoop.bannerspace;

import com.adscoop.bannerspace.config.ConfigBinder;
import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
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

public  static void main(String ... args ) throws Exception {
    RxRatpack.initialize();
    RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.registry(Guice.registry(rs -> rs.module(ConfigBinder.class).module(ServiceCommonConfigModule.class))).
            serverConfig(serverConfigBuilder -> serverConfigBuilder.baseDir(BaseDir.find()).yaml("datasource.yaml").require("db", Config.class).props("ratpack.properties").env().sysProps().build()).handlers(chain -> {
        chain.prefix("banner" , chain1 -> {
            chain1.post("create",CreateBannerHandler.class);


        }).prefix("website",webchain-> {
            webchain.post("create/:companyname", CreateWebSiteHandler.class);

        });

    }));

    }

}


