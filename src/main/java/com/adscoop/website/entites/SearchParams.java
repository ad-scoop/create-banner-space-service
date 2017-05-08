package com.adscoop.website.entites;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Getter
@Setter
public class SearchParams {

	private String name;

	@Builder.Default
	List<String> names = newArrayList();

}
