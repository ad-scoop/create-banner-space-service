package com.adscoop.website.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.UserNode;
import com.google.inject.Inject;

import ratpack.exec.Promise;

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
        try {

            return Optional.ofNullable(session.queryForObject(UserNode.class, "match (u) where u.email='" + email + "' return u", Collections.emptyMap()));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }


    @Override
    public Optional<UserNode> findUserbyBannerId(Long id) throws Exception {
        return null;
    }

    @Override
    public Promise<UserNode> findUserByToken(String token) throws Exception {
        try {
            Map<String,String> stringMap = new HashMap<>();
            stringMap.put("token",token);
            return Promise.value(session.queryForObject(UserNode.class,"match (u:UserNode  {token:{token}}) return u",stringMap));
        } catch (Exception e) {
            throw new Exception(e.getMessage());

        }
    }


    @Override
    public void save(UserNode userNode) throws Exception {
        try {
            session.save(userNode);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
