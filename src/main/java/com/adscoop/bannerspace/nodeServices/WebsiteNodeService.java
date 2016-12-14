package com.adscoop.bannerspace.nodeServices;

import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.adscoop.services.neo4j.connection.ServiceCommonConfig;
import com.google.inject.Inject;
import com.kleistit.entiites.user.Company;
import com.kleistit.entiites.user.WebSiteNode;
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

    private ConnectionSource session;

    @Inject
    public WebsiteNodeService(ConnectionSource session) {
        this.session = session;
    }


    public void save(WebSiteNode webSiteNode){
        session.session().save(webSiteNode);


    }

    public WebSiteNode findByPath(String path){
       return  session.session().queryForObject(WebSiteNode.class,"match (w:WebSiteNode) where w.path="+ path+" return w", Collections.EMPTY_MAP);
    }

    public List<WebSiteNode> findByCompanyName(String companyname){
        List<WebSiteNode> webSiteNodes = new ArrayList<>();
        try {
            Result result = session.session().query("", Collections.EMPTY_MAP);

            Iterator iterator = result.iterator();
            while (iterator.hasNext()) {
                webSiteNodes.add((WebSiteNode) iterator.next());

            }

        } catch (Exception e){

        }
        return webSiteNodes;

    }


}
