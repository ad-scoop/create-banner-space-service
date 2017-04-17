package com.adscoop.bannerspace.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
@JsonIgnoreProperties( ignoreUnknown = true)
public class UserNode extends Entity {

    @Getter
    @Setter
    private String firstname;

    @Getter
    @Setter
    private String middlename;

    @Getter
    @Setter
    private boolean isActivated;

    @Getter
    @Setter
    private String lastname;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    @Labels
    private List<String> labels = new ArrayList<>();


    @Getter
    @Setter
    @JsonIgnore
    @Relationship(type = "USER_HAS_WEBSITE")
    private List<WebSite> webSites = new ArrayList<>();

    @Getter
    @Setter
    private boolean activated;

    public UserNode activated() {
        this.activated = true;
        return this;
    }

    public void addWebSite(WebSite webSite) {
        webSites.add(webSite);
        webSite.getUserNodes().add(this);
    }


}
