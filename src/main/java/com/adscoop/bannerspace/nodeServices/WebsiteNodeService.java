package com.adscoop.bannerspace.nodeServices;

import com.adscoop.entiites.Company;
import com.adscoop.entiites.WebSiteNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;

import com.google.inject.Inject;

import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import java.util.*;

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

    public Optional<Company> findCompanyFindByName(String token, String companyname){

        return Optional.of(session.session().queryForObject(Company.class,"match (c:Company)<-[:USER_HAS_COMPANY]-(u:UserNode) where u.token = '"+token+"' and c.companyname='"+companyname+"' return c", Collections.EMPTY_MAP));
    }



    public Optional<WebSiteNode> findByCompanyName(String companyName){
      return  Optional.of(session.session().queryForObject(WebSiteNode.class, "match (c:Company)-[:COMPANY_HAS_WEBSITE]->(w:WebSiteNode) where c.companyname='"+companyName+"' return w limit 1",Collections.EMPTY_MAP ));

    }



}
