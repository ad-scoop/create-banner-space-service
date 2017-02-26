package com.adscoop.bannerspace.services;


import com.adscoop.bannerspace.entites.BannerSpace;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

import java.io.IOException;

/**
 * Created by thokle on 29/10/2016.
 */
public class CreateBannerSpaceService {

    Session connectionSource;


    @Inject
    public CreateBannerSpaceService(Session connectionSource) {
        this.connectionSource = connectionSource;
    }


    public void createBannerSpace(BannerSpace bannerSpace) throws IOException {
        connectionSource.save(bannerSpace);
    }


    public BannerSpace findById(Long id) {
        return null;
    }


}
