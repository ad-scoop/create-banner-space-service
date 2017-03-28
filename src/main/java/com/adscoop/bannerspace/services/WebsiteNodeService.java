package com.adscoop.bannerspace.services;

import com.adscoop.bannerspace.entites.WebSiteNode;

import ratpack.exec.Promise;

/**
 * Created by thokle on 25/02/2017.
 */
public interface WebsiteNodeService {
    void save(com.adscoop.bannerspace.entites.WebSiteNode webSiteNode);

    Promise<WebSiteNode> findByHostName(String path) throws Exception;



    Promise<WebSiteNode> findWebSiteByUserTokenAndHostname(String token, String hostname) throws Exception;

    Promise<Iterable<WebSiteNode>> findWebSitesByToken(String token) throws  Exception;
}
