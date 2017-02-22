package com.adscoop.bannerspace;

import com.adscoop.bannerspace.chains.WebSiteChainAction;
import com.adscoop.bannerspace.config.ConfigBinder;
import com.adscoop.com.adscoop.services.AuthConfigurableModule;
import com.adscoop.services.neo4j.connection.Config;
import com.adscoop.services.neo4j.connection.ServiceCommonConfigModule;
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
    RatpackServer.start(spec -> spec.serverConfig(sf -> sf.baseDir(BaseDir.find()).yaml("ratpack.yaml").sysProps().env().require("/db", Config.class)).registry(Guice.registry(rs -> rs.module(ConfigBinder.class).module(ServiceCommonConfigModule.class).module(AuthConfigurableModule.class))).handlers( chain -> chain.prefix("banner",WebSiteChainAction.class)));


}
}


