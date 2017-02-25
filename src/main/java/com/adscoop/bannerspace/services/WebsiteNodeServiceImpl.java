package com.adscoop.bannerspace.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.adscoop.bannerspace.entites.Category;
import com.adscoop.bannerspace.entites.TargetGroups;
import com.adscoop.bannerspace.entites.UserNode;
import org.neo4j.ogm.session.Session;

import com.adscoop.bannerspace.entites.WebSiteNode;
import com.google.inject.Inject;

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
    public void save(com.adscoop.bannerspace.entites.WebSiteNode webSiteNode){
        session.save(webSiteNode);


    }

    @Override
    public WebSiteNode findByPath(String path){
       return  session.queryForObject(WebSiteNode.class,"match (w:WebSiteNode) where w.path="+ path+" return w", Collections.EMPTY_MAP);
    }





    @Override
    public Optional<WebSiteNode> findByCompanyName(String companyName){
      return  Optional.of(session.queryForObject(WebSiteNode.class, "match (c:Company)-[:COMPANY_HAS_WEBSITE]->(w:WebSiteNode) where c.companyname='"+companyName+"' return w limit 1",Collections.EMPTY_MAP ));

    }


    @Override
    public Optional<Iterable<WebSiteNode>> finByCriteria(List<Category> categoryList, List<TargetGroups> targetGroups) throws Exception{
        try{


            return  Optional.ofNullable(session.query(WebSiteNode.class,"match (w) where w.category in"+categoryList+ " and w.targetgroups in "+targetGroups+" return w ",Collections.EMPTY_MAP));

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }

    }


    @Override
    public Optional<WebSiteNode> findWebSiteByUserToken(String token) throws Exception{
        try{
            return Optional.ofNullable(session.queryForObject(WebSiteNode.class,"match (w:WebSiteNode)<-[:USER_HAS_WEBSITE]-(u:UserNode) where u.token='"+token+"' return w",Collections.EMPTY_MAP));
        }   catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
