package com.adscoop.bannerspace.nodeServices;


import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;

import java.util.Collection;

/**
 * Created by thokle on 25/11/2016.
 */
public class ReserveBannerSpace {


    private Session connectionSource;

    @Inject
    public ReserveBannerSpace(Session connectionSource) {
        this.connectionSource = connectionSource;
    }





}
