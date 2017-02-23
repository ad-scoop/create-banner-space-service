package com.adscoop.bannerspace.services;

import java.util.Optional;

import com.adscoop.bannerspace.entites.BannerNode;

/**
 * Created by kleistit on 21/02/2017.
 */
public interface BannerNodeService  {

    Optional<BannerNode> findbyId(Long id) throws Exception;

    Optional<BannerNode> saveOrUpdate(BannerNode bannerNode);

}
