package com.adscoop.website.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by thokle on 28/04/2017.
 */
@NodeEntity
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region extends AbstractEntity {


    @Getter
    @Setter
    private String city;
    @Getter
    @Setter
    private String region;
    @Getter
    @Setter
    private String country;

    @Getter
    @Setter
    @Relationship(type = "WEBSITE_HAS_REGIONS")
    private List<WebSite> webSiteList = newArrayList();




}