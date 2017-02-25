package com.adscoop.bannerspace.services;

import java.util.Collections;
import java.util.Optional;

import com.google.inject.Inject;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.neo4j.ogm.session.Session;

import com.adscoop.bannerspace.entites.UserNode;

/**
 * Created by kleistit on 21/02/2017.
 */
public class UserServiceImpl implements UserService {

    private Session session;

@Inject
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

    @Override
    public Optional<UserNode> findUserByToken(String token) throws Exception {
        try{
           return Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where u.token='"+token+"' return u", Collections.EMPTY_MAP));
        }catch (Exception e){
            throw new Exception(e.getMessage());

        }
    }


    @Override
    public void save(UserNode userNode) throws Exception {
        try{
            session.save(userNode);
        }catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }
}
