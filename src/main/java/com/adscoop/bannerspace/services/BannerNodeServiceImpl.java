package com.adscoop.bannerspace.services;

import java.util.Collections;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.bannerspace.entites.BannerNode;

/**
 * Created by kleistit on 21/02/2017.
 */
public class BannerNodeServiceImpl implements BannerNodeService {

    private Session session;

    public BannerNodeServiceImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<BannerNode> findbyId(Long id) throws Exception {
        try{
        return Optional.ofNullable(session.queryForObject(BannerNode.class,"match (b:bannerNodefit) where ID(b)="+id+" return b", Collections.EMPTY_MAP));
    }catch (Exception e){
        throw  new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<BannerNode> saveOrUpdate(BannerNode bannerNode) {
         session.save(bannerNode);

        return Optional.ofNullable(session.load(BannerNode.class, bannerNode.getId()));
    }
}
