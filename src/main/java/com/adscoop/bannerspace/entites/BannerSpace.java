package com.adscoop.bannerspace.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thokle on 24/08/2016.
 */
@NodeEntity
public class BannerSpace extends Entity {


    private String positionSiteM;

    private String positionSiteL;

    private Double price;

    private int width;

    private int heihht;




    @Labels
    private List<String> labels = new ArrayList<>();


    @Relationship(type = "BELONGS_TO_WEBSITE", direction = Relationship.INCOMING)
    private Set<WebSiteNode> webSiteNodeSet = new HashSet<>();


    private List<String> categories = new ArrayList<>();

    private List<String> targetgroups = new ArrayList<>();



    public String getPositionSiteM() {
        return positionSiteM;
    }

    public void setPositionSiteM(String positionSiteM) {
        this.positionSiteM = positionSiteM;
    }

    public String getPositionSiteL() {
        return positionSiteL;
    }

    public void setPositionSiteL(String positionSiteL) {
        this.positionSiteL = positionSiteL;
    }




    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setLabel(String label) {
        this.labels.add(label);
    }



    @JsonIgnore
    public Set<WebSiteNode> getWebSiteNodeSet() {
        return webSiteNodeSet;
    }

    public void setWebSiteNodeSet(Set<WebSiteNode> webSiteNodeSet) {
        this.webSiteNodeSet = webSiteNodeSet;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<String> getTargetgroups() {
        return targetgroups;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeihht() {
        return heihht;
    }

    public void setHeihht(int heihht) {
        this.heihht = heihht;
    }
}
