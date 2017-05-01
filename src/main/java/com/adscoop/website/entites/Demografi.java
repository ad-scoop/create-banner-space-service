package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import org.neo4j.ogm.annotation.NodeEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demografi extends AbstractEntity {

	@Builder.Default
    private List<Gender> genders  = newArrayList();
	private int minAge;
	private int maxAge;

}
