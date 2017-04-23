package com.adscoop.website.services;

import java.util.Collection;

import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.handlers.Const;
import com.google.inject.Inject;

import ratpack.exec.Promise;

public class WebsiteService {

    private Session session;

    @Inject
    public WebsiteService(Session session) {
        this.session = session;
    }

    public void save(WebSite webSite) {
        session.save(webSite);
        session.clear();
    }

    public Promise<Boolean> hostNameExists(String hostname) {
        return Promise.value(findSingelByHost(hostname).isNotEmpty());
    }
    
    public Promise<WebSite> findByHostName(String hostname) {
    	return Promise.value(findSingelByHost(hostname));
    }

    public Promise<Iterable<WebSite>> findByToken(String token) {
        return Promise.value(session.loadAll(WebSite.class, new Filter("token", token)));
    }

    public void delete(String host) {
        WebSite webSite = this.findSingelByHost(host);
        if (webSite != null) {
        	session.delete(webSite);
        	session.clear();
        }
    }
    
	private WebSite findSingelByHost(String hostname) {
		Collection<WebSite> all = session.loadAll(WebSite.class, new Filter(Const.Parameter.HOST, hostname));
    	if (all.isEmpty()) {
    		return WebSite.builder().build();
    	} else {
    		return all.iterator().next();
    	}
	}

}
