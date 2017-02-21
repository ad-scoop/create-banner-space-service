package com.adscoop.bannerspace.services;


import com.adscoop.bannerspace.entites.WebSiteNode;
import com.google.inject.Inject;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thokle on 29/10/2016.
 */
public class WebsiteNodeService {

    private Session session;

    @Inject
    public WebsiteNodeService(Session session) {
        this.session = session;
    }


    public void save(WebSiteNode webSiteNode){
        session.save(webSiteNode);


    }

    public WebSiteNode findByPath(String path){
       return  session.queryForObject(WebSiteNode.class,"match (w:WebSiteNode) where w.path="+ path+" return w", Collections.EMPTY_MAP);
    }

    public List<WebSiteNode> findByCompanyName(String companyname){
        List<WebSiteNode> webSiteNodes = new ArrayList<>();
        try {
            Result result = session.query("", Collections.EMPTY_MAP);

            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                webSiteNodes.add((WebSiteNode) iterator.next());

            }

        } catch (Exception e){

        }
        return webSiteNodes;

    }


}
