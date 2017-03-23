package com.adscoop.bannerspace.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by thokle on 18/10/2016.
 */
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebSiteNode extends Entity {


    @Setter
    @Getter
    private int port;

    @Getter
    @Setter
    private String hostname;

    @Getter
    @Setter
    private String path;

    @Getter
    @Setter
    private String userToken;


    @Getter
    @Setter
    @Relationship(type = "WEBSITE_HAS_BANNERSPACE", direction = Relationship.OUTGOING)
    private List<BannerSpace> bannerSpaceSet = new ArrayList<>();


    @JsonIgnore
    @Getter
    @Setter
    @Relationship(type = "USER_HAS_WEBSITE ", direction = Relationship.INCOMING)
    private List<UserNode> userNodes = new ArrayList<>();




    @Getter
    @Setter
    @Relationship(type = "WEBSITE_REGIONS", direction = Relationship.OUTGOING)
    private List<Regions> regionss = new ArrayList<>();


    public void addBannerSpace(BannerSpace bannerSpace) {
        bannerSpaceSet.add(bannerSpace);
        bannerSpace.getWebSiteNodeSet().add(this);
    }

    

    public void addRegion(Regions regions) {
        regionss.add(regions);
        regions.getWebSiteNodes().add(this);

    }
}
