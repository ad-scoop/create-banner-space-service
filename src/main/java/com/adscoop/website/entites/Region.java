package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    @Builder.Default
    private List<WebSite> webSiteList = newArrayList();

}