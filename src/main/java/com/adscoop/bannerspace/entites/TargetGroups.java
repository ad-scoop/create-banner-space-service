package com.adscoop.bannerspace.entites;

import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotations.Labels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by thokle on 25/08/2016.
 */
public class TargetGroups extends Entity {


    private String gender;
    private int from;
    private int to;

    @Labels
    private List<String> labels = new ArrayList<>();


    @Relationship(type = "WEBSITE_TARGETGROUPT", direction = Relationship.INCOMING)
    private Set<WebSiteNode> websiteNodes = new HashSet<>();

    @Relationship(direction = Relationship.INCOMING, type = "TARGET_BELONGS_TO_BANNER")
    private Set<BannerNode> tarbannerNodes = new HashSet<>();


    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }


    public Set<BannerNode> getTarbannerNodes() {
        return tarbannerNodes;
    }

    public void setTarbannerNodes(Set<BannerNode> tarbannerNodes) {
        this.tarbannerNodes = tarbannerNodes;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }


    public Set<WebSiteNode> getWebsiteNodes() {
        return websiteNodes;
    }

    public void setWebsiteNodes(Set<WebSiteNode> websiteNodes) {
        this.websiteNodes = websiteNodes;
    }
}
