package com.adscoop.bannerspace.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private Set<WebSiteNode> webSiteNodeSet = new HashSet<>();

    @Getter
    @Setter
    private List<String> categories = new ArrayList<>();

    @Getter
    @Setter
    private List<String> targetgroups = new ArrayList<>();





    @JsonIgnore
    public Set<WebSiteNode> getWebSiteNodeSet() {
        return webSiteNodeSet;
    }

}
