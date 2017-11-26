package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.Properties;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demografi extends AbstractEntity {

	@Properties
	@Builder.Default
    private Map<String,Object> genders  = new HashMap();
	@Setter
	@Getter
	private int minAge;
	@Setter
	@Getter
	private int maxAge;

}
