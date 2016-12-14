package com.adscoop.bannerspace;

import com.adscoop.bannerspace.config.ConfigBinder;
import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;
import ratpack.guice.Guice;
import ratpack.rx.RxRatpack;
import ratpack.server.RatpackServer;

/**
 * Created by thokle on 16/10/2016.
 */
public class StartApp {

public  static void main(String ... args ) throws Exception {
    RxRatpack.initialize();
    RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.registry(Guice.registry(rs -> rs.module(ConfigBinder.class).module(ServiceCommonConfig.class))).
            serverConfig(serverConfigBuilder -> serverConfigBuilder.port(8383).env("banner-creator")).handlers(chain -> {
        chain.prefix("banner" , chain1 -> {
            chain1.post("create",CreateBannerHandler.class);


        }).prefix("website",webchain-> {
            webchain.post("create/:companyname", CreateWebSiteHandler.class);

        });

    }));

    }

}


