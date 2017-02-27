package com.adscoop.bannerspace;

import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.config.ConfigBinder;
import com.adscoop.bannerspace.handlers.banner.CreateBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.bannerspace.AddBannerSpaceToWebsite;
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

        RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(serverConfigBuilder ->
                serverConfigBuilder.baseDir(BaseDir.find()).yaml("datasource.yaml").require("/db", Config.class).props("ratpack.properties").env().sysProps().development(false).build())
                .registry(Guice.registry(bindingsSpec -> bindingsSpec.module(ConfigBinder.class).module(ServiceCommonConfigModule.class))).handlers(chain -> chain.prefix("banner", ba ->
                        ba.post(CreateBannerSpaceHandler.class)
                ).prefix("reserve", res -> res.post(":email/:bid", ReserveBannerSpaceHandler.class)).prefix("website", WebSiteChainAction.class).prefix("bannerspace", bach -> {
                    bach.post(AddBannerSpaceToWebsite.class);

                }))
        );

    }


}


