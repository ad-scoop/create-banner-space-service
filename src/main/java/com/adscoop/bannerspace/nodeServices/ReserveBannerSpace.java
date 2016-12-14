package com.adscoop.bannerspace.nodeServices;

import com.adscoop.entiites.BannerSpace;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;

import java.util.Collection;

/**
 * Created by thokle on 25/11/2016.
 */
public class ReserveBannerSpace {


    private ConnectionSource connectionSource;

    @Inject
    public ReserveBannerSpace(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }





}
u