package com.adscoop.website.entites;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class WebSite extends Entity {

	@Getter
	@Setter
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

	@Relationship(type = "WEBSITE_HAS_BANNERSPACE")
	private List<BannerSpace> bannerSpaceSet = new ArrayList<>();

	@Getter
	@Setter
	@JsonIgnore
	@Relationship(type = "USER_HAS_WEBSITE",direction = Relationship.INCOMING)
	private List<UserNode> userNodes = new ArrayList<>();

	@Relationship(type = "WEBSITE_REGIONS")
	private List<Regions> regionss = new ArrayList<>();

	public void addBannerSpace(BannerSpace bannerSpace) {
		bannerSpaceSet.add(bannerSpace);
		bannerSpace.getWebSiteSet().add(this);
	}

	public void addRegion(Regions regions) {
		regionss.add(regions);
		regions.getWebSites().add(this);
	}

}
