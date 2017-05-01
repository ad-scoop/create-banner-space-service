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
import lombok.NoArgsConstructor;
import lombok.Setter;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebSite extends AbstractEntity {


	private String host;
	
	private int port;
	private String path;
	private String url;
	private boolean accepted;
	@Setter
	private String token;

	@Relationship(type = "SEX", direction = Relationship.INCOMING)
	private Demografi demografi;

	@Relationship(type = "PLACE", direction = Relationship.INCOMING)
	private Area area;

	@Relationship(type = "COOPERATION", direction = Relationship.INCOMING)
	private Organisation organisation;

	@Relationship(type = "WEBSITE_HAS_BANNERSPACE")
	@Builder.Default
	private List<BannerSpace> bannerSpaces = newArrayList();

	@Relationship(type = "WEBSITE_HAS_REGIONS")
	@Builder.Default
	private List<Region> regions = newArrayList();
	



	@Labels
	@Builder.Default
	public List<String> labels = newArrayList();
	
	public boolean isEmpty() {
		return StringUtils.isEmpty(url);
	}

	public boolean isNotEmpty() {
		return StringUtils.isNotEmpty(url);
	}


}
