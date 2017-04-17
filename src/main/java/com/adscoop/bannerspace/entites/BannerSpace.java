package com.adscoop.bannerspace.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by thokle on 24/08/2016.
 */
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class BannerSpace extends Entity {


    @Getter
    @Setter
    private String position;


    @Getter
    @Setter
    private Double price;

    @Getter
    @Setter
    private int width;

    @Getter
    @Setter
    private int height;




    @Labels
    private List<String> labels = new ArrayList<>();


    @Relationship(type = "BELONGS_TO_WEBSITE", direction = Relationship.INCOMING)
    private Set<WebSite> webSiteSet = new HashSet<>();

    @Getter
    @Setter
    private List<String> categories = new ArrayList<>();

    @Getter
    @Setter
    private List<String> targetgroups = new ArrayList<>();





    @JsonIgnore
    public Set<WebSite> getWebSiteSet() {
        return webSiteSet;
    }

}
