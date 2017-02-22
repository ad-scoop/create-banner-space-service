package com.adscoop.bannerspace.services;


import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

/**
 * Created by thokle on 25/11/2016.
 */
public class ReserveBannerSpaceServiceImpl {


    private Session connectionSource;

    @Inject
    public ReserveBannerSpaceServiceImpl(Session connectionSource) {
        this.connectionSource = connectionSource;
    }





}
