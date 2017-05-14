package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demografi extends AbstractEntity {

	@Builder.Default
    private List<String> genders  = newArrayList();
	@Setter
	@Getter
	private int minAge;
	@Setter
	@Getter
	private int maxAge;

}
