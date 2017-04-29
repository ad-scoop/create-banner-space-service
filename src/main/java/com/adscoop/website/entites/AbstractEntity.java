package com.adscoop.website.entites;

import org.neo4j.ogm.annotation.GraphId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractEntity {

	@GraphId
	private Long id;

	public AbstractEntity setId(Long id) {
		this.id = id;
		return this;
	}
	
}
