package com.adscoop.bannerspace.services;

import java.util.Optional;

import com.adscoop.bannerspace.entites.UserNode;

/**
 * Created by kleistit on 21/02/2017.
 */
public interface UserService {

    Optional<UserNode> findUserByEmail(String email) throws Exception;
    Optional<UserNode> findUserbyBannerId(Long id) throws Exception;
    Optional<UserNode> findUserByToken(String token) throws Exception;
    void save(UserNode userNode) throws Exception;

}
