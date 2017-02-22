package com.adscoop.bannerspace.nodeServices;

import com.adscoop.entiites.BannerNode;
import com.adscoop.entiites.BannerSpace;
import com.adscoop.entiites.UserNode;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.*;

/**
 * Created by thokle on 25/11/2016.
 */
public class ReserveBannerSpaceService {

    String query = " " ;

    private ConnectionSource connectionSource;

    @Inject
    public ReserveBannerSpaceService(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }



    public Optional<List<BannerNode>>  getBanrnerNodesromId(String usertoken){
        // match (b:BannerNode)<-[:]
        return null;

    }


    public  BannerNode save(BannerNode bannerNode){
            connectionSource.session().save(bannerNode);
            return  bannerNode;

    }



    public Optional<UserNode> findUserByBannerDomain(String domainname){

        return Optional.of(connectionSource.session().queryForObject(UserNode.class," match (u:UserNode)-[:USER_HAS_COMPANY]->(c:Company)-[:COMPANY_HAS_WEBSITE]->(w:Website)-[:BELONGS_TO_WEBSITE]->(b:BannerSpace)  where b.domain='n"+domainname +"  return u",Collections.EMPTY_MAP));
    }
}
