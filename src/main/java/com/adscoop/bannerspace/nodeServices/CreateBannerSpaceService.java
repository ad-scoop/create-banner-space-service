package com.adscoop.bannerspace.nodeServices;

import com.adscoop.entiites.BannerSpace;
import com.adscoop.services.neo4j.connection.ConnectionSource;

import com.google.inject.Inject;


import java.io.IOException;

/**
 * Created by thokle on 29/10/2016.
 */
public class CreateBannerSpaceService {

        ConnectionSource connectionSource;


    @Inject
    public CreateBannerSpaceService(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }


    public void createBannerSpace(BannerSpace bannerSpace) throws IOException {
            connectionSource.session().save(bannerSpace);
    }




    public  BannerSpace findById(Long id){
     return null;
    }


}
