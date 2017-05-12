package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.neo4j.ogm.annotation.Labels;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@AllArgsConstructor
public class WebSite extends AbstractEntity {

	private int port;
	private String path;
	private String url;
	private boolean accepted;
	@Setter
	private String token;

	@Relationship(type = "SEX")
	@Builder.Default
	private Demografi demografi = new Demografi();

	@Relationship(type = "PLACE")
	@Builder.Default
	private List<Area> areas = newArrayList();

	@Relationship(type = "COOPERATION", direction = Relationship.INCOMING)
	@Builder.Default
	private Organisation organisation = new Organisation();

	@Relationship(type = "WEBSITE_HAS_BANNERSPACE")
	@Builder.Default
	private List<BannerSpace> bannerSpaces = newArrayList();

	@Labels
	@Builder.Default
	private List<String> labels = newArrayList();
	
	public WebSite() {
		this.demografi = new Demografi();
		this.areas = newArrayList();
		this.organisation = new Organisation();
		this.bannerSpaces = newArrayList();
	}
	
	public boolean isEmpty() {
		return StringUtils.isEmpty(url);
	}

	public boolean isNotEmpty() {
		return StringUtils.isNotEmpty(url);
	}

}
