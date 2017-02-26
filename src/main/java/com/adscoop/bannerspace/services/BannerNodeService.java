package com.adscoop.bannerspace.services;

import com.adscoop.bannerspace.entites.BannerNode;

import java.util.Optional;

/**
 * Created by kleistit on 21/02/2017.
 */
public interface BannerNodeService {

    Optional<BannerNode> findbyId(Long id) throws Exception;

    Optional<BannerNode> saveOrUpdate(BannerNode bannerNode);

}
