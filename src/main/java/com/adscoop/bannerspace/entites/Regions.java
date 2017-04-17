package com.adscoop.bannerspace.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by thokle on 25/08/2016.
 */
@NodeEntity
public class Regions extends Entity {

    private String regionname;
    private String country;
    private long logitude;
    private long lattiude;


    @Labels
    private List<String> labels = new ArrayList<>();

    @Relationship(direction = Relationship.OUTGOING, type = "REGION_BELONGS_TO_BANNER_SPACE")
    private Set<BannerSpace> bannerSpaces = new HashSet<>();

    @Relationship(direction = Relationship.OUTGOING, type = "WEBSITE_REGIONS")
    private Set<WebSite> webSites = new HashSet<>();

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonIgnore
    public Set<BannerSpace> getBannerSpaces() {
        return bannerSpaces;
    }

    public void setBannerSpaces(Set<BannerSpace> bannerSpaces) {
        this.bannerSpaces = bannerSpaces;
    }

    @JsonIgnore
    public Set<WebSite> getWebSites() {
        return webSites;
    }

    public void setWebSites(Set<WebSite> webSites) {
        this.webSites = webSites;
    }


    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getLogitude() {
        return logitude;
    }

    public void setLogitude(long logitude) {
        this.logitude = logitude;
    }

    public long getLattiude() {
        return lattiude;
    }

    public void setLattiude(long lattiude) {
        this.lattiude = lattiude;
    }
}
