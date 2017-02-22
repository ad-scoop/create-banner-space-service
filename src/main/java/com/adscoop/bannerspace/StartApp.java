package com.adscoop.bannerspace;

import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.config.ConfigBinder;
<<<<<<< HEAD
import com.adscoop.com.adscoop.services.AuthConfigurableModule;
import com.adscoop.services.neo4j.connection.Config;
import com.adscoop.services.neo4j.connection.ServiceCommonConfigModule;
=======
import com.adscoop.bannerspace.handlers.banner.CreateBannerHandler;
import com.adscoop.bannerspace.handlers.banner.ReserveBannerSpaceHandler;
import com.adscoop.bannerspace.handlers.website.CreateWebSiteHandler;
import com.adscoop.bannerspace.modules.Config;
import com.adscoop.bannerspace.modules.ServiceCommonConfigModule;

>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
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
<<<<<<< HEAD
    RatpackServer.start(spec -> spec.serverConfig(sf -> sf.baseDir(BaseDir.find()).yaml("ratpack.yaml").sysProps().env().require("/db", Config.class)).registry(Guice.registry(rs -> rs.module(ConfigBinder.class).module(ServiceCommonConfigModule.class).module(AuthConfigurableModule.class))).handlers( chain -> chain.prefix("banner",WebSiteChainAction.class)));


}
=======
    RatpackServer.start(ratpackServerSpec -> ratpackServerSpec.serverConfig(serverConfigBuilder ->
        serverConfigBuilder.baseDir(BaseDir.find()).yaml("datasource.yaml").require("/db",Config.class).props("ratpack.properties").env().sysProps().development(false).build())
            .registry(Guice.registry(bindingsSpec ->  bindingsSpec.module(ConfigBinder.class).module(ServiceCommonConfigModule.class))).handlers(chain -> chain.prefix("banner", ba ->
            ba.post(CreateBannerHandler.class)
            ).prefix("reserve", res -> res.post(":email/:idx",ReserveBannerSpaceHandler.class)))
    );

    }

>>>>>>> 864a243ee82c65a8969aa46223a6b9d5ae852251
}


