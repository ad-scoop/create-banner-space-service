package com.adscoop.bannerspace.services;

import com.adscoop.bannerspace.entites.WebSiteNode;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;

import java.util.Collections;
import java.util.List;

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
    public void save(com.adscoop.bannerspace.entites.WebSiteNode webSiteNode) {
        session.save(webSiteNode);


    }

    @Override
    public Promise<WebSiteNode> findByHostName(String hostname) throws Exception {
        try{
        return Promise.value(session.queryForObject(WebSiteNode.class, "match (w:WebSiteNode {hostname:'{hostname}'}) return w",Collections.singletonMap("hostname",hostname)));
    }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

    @Override
    public Promise<WebSiteNode> findWebSiteByUserTokenAndHostname(String token, String hostname) throws Exception {
        try {
            return Promise.value(session.queryForObject(WebSiteNode.class, "match (w:WebSiteNode )<-[:USER_HAS_WEBSITE]-(u:UserNode) where u.token='" + token + "' and w.hostname CONTAINS '"+hostname+"' return w ", Collections.EMPTY_MAP));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Promise<Iterable<WebSiteNode>> findWebSitesByToken(String token) throws Exception {
        return Promise.value(session.query(WebSiteNode.class,"match (u:UserNode {token:'{token}'})-[:USER_HAS_WEBSITE]->(w:WebSiteNode)  return w",Collections.singletonMap("token",token)));
    }
}
