package com.adscoop.website.services;

import com.adscoop.website.entites.WebSite;

import com.adscoop.website.handlers.Const;
import com.google.inject.Inject;
import lombok.Generated;
import lombok.Setter;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by thokle on 25/02/2017.
 */

public class WebsiteService {


    private Session session;

    @Setter
    private String  query;
    @Inject
    public WebsiteService(Session session) {
        this.session = session;
    }

    public void save(WebSite webSite) {
        session.save(webSite);
        session.clear();
    }

    public Promise<Boolean> urlNameExists(String url) {
        return Promise.value(findSingelByUrl(url).isNotEmpty());
    }

    public Promise<WebSite> findByUrlName(String url) {
        return Promise.value(findSingelByUrl(url));
    }

    public Promise<Iterable<WebSite>> findByToken(String token) {
        return Promise.value(session.loadAll(WebSite.class, new Filter("token", token)));
    }

    public void delete(String url) {
        WebSite webSite = this.findSingelByUrl(url);
        if (webSite != null) {
            session.delete(webSite);
            session.clear();
        }
    }


    public  Promise<Iterable<WebSite>> findByNames(List<String> names)
        {
            //return  Promise.value(session.queryForObject(WebSite.class,"match (w:WebSite) where w.url =~ {names} return w",Collections.singletonMap("names","ad")));
                return null;
        }

    private WebSite findSingelByUrl(String url) {
        Collection<WebSite> all = session.loadAll(WebSite.class, new Filter(Const.Parameter.URL, url));
        if (all.isEmpty()) {
            return WebSite.builder().build();
        } else {
            return all.iterator().next();
        }
    }


    private String queryBuilder(List<String> names){


         return query;
    }
}
