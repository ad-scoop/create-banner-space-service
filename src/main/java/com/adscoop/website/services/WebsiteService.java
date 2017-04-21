package com.adscoop.website.services;

import com.adscoop.website.entites.WebSite;

import ratpack.exec.Promise;

/**
 * Created by thokle on 25/02/2017.
 */
public interface WebsiteService {
	void save(WebSite webSite);

	Promise<WebSite> findByHostName(String path) throws Exception;

	Promise<WebSite> findWebSiteByUserTokenAndHostname(String token, String hostname) throws Exception;

	Promise<Iterable<WebSite>> findWebSitesByToken(String token) throws Exception;

	void delete(WebSite webSite);
}
