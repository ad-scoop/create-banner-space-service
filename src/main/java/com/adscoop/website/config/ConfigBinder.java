package com.adscoop.website.config;

import com.adscoop.website.chains.SearchChain;
import com.adscoop.website.chains.WebSiteChainAction;
import com.adscoop.website.handlers.CORSHandler;
import com.adscoop.website.handlers.CreateWebSiteHandler;
import com.adscoop.website.handlers.DeleteWebSiteHandler;
import com.adscoop.website.handlers.GetWebSitesHandler;
import com.adscoop.website.handlers.GetWebsiteHandler;
import com.adscoop.website.handlers.UpdateWebSisteHandler;
import com.adscoop.website.handlers.WebSiteSearchHandler;
import com.adscoop.website.services.SearchService;
import com.adscoop.website.services.WebsiteService;
import com.google.inject.AbstractModule;

public class ConfigBinder extends AbstractModule {

	@Override
	protected void configure() {

		bind(CreateWebSiteHandler.class).asEagerSingleton();

		bind(CORSHandler.class).asEagerSingleton();
		bind(WebSiteChainAction.class).asEagerSingleton();
		bind(SearchChain.class).asEagerSingleton();
		bind(GetWebsiteHandler.class).asEagerSingleton();
		bind(GetWebSitesHandler.class).asEagerSingleton();
		bind(DeleteWebSiteHandler.class).asEagerSingleton();
		bind(UpdateWebSisteHandler.class).asEagerSingleton();
		bind(WebSiteSearchHandler.class).asEagerSingleton();
		bind(WebsiteService.class).asEagerSingleton();
		bind(SearchService.class).asEagerSingleton();
	}

}
