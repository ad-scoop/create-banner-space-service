package com.adscoop.bannerspace.services;

import com.adscoop.bannerspace.entites.UserNode;
import org.neo4j.ogm.session.Session;

import java.util.Collections;
import java.util.Optional;

/**
 * Created by kleistit on 21/02/2017.
 */
public class UserServiceImpl implements UserService {

    private Session session;


    public UserServiceImpl(Session session) {
        this.session = session;
    }

    @Override
    public Optional<UserNode> findUserByEmail(String email) throws Exception {
       try{
           return Optional.ofNullable(session.queryForObject(UserNode.class,"match (u) where u.email='"+email+"' return u", Collections.EMPTY_MAP));
       } catch (Exception e){
           throw new Exception(e.getMessage());
       }

    }


    @Override
    public Optional<UserNode> findUserbyBannerId(Long id) throws Exception {
        return null;
    }
}
