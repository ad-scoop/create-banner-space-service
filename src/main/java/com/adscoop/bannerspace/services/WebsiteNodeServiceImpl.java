package com.adscoop.bannerspace.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.neo4j.ogm.session.Session;

import com.adscoop.bannerspace.entites.WebSite;
import com.google.inject.Inject;

import ratpack.exec.Promise;

/**
 * Created by thokle on 29/10/2016.
 */
public class WebsiteNodeServiceImpl implements WebsiteNodeService {

    private Session session;

    @Inject
    public WebsiteNodeServiceImpl(Session session) {
        this.session = session;
    }


    @Override
    public void save(WebSite webSite) {
        session.save(webSite);


    }

    @Override
    public Promise<WebSite> findByHostName(String hostname) throws Exception {
        try{
        return Promise.value(session.queryForObject(WebSite.class, "match (w:WebSiteNode {hostname:'{hostname}'}) return w",Collections.singletonMap("hostname",hostname)));
    }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public Promise<WebSite> findWebSiteByUserTokenAndHostname(String token, String hostname) throws Exception {
        try {
            Map<String, Object> stringObjectMap = new HashMap<>();
            stringObjectMap.put("hostname",hostname);
            stringObjectMap.put("token",token);
            return Promise.value(session.queryForObject(WebSite.class, "match (u:UserNode {token:{token}} )-[:USER_HAS_WEBSITE]->(w:WebSite {hostname:{hostname}})  return w, u ", stringObjectMap));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Promise<Iterable<WebSite>> findWebSitesByToken(String token) throws Exception {
        return Promise.value(session.query(WebSite.class,"match (u:UserNode {token:{token}})-[:USER_HAS_WEBSITE]->(w:WebSite) return w,u",Collections.singletonMap("token",token)));
    }


    @Override
    public void delete(WebSite webSite) {

        session.detachNodeEntity(webSite.getId());
        session.delete(webSite);
    }
}
