package com.adscoop.bannerspace.entites;

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

    private Set<WebSiteNode> webSiteNodes = new HashSet<>();

    @Getter
    @Setter
    private boolean activated;

    public UserNode activated() {
        this.activated = true;
        return this;
    }

    public void addWebSite(WebSiteNode webSiteNode) {
        webSiteNodes.add(webSiteNode);
        webSiteNode.getUserNodes().add(this);
    }


}
