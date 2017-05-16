package com.adscoop.website.services;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.entites.WebSite;

import ratpack.exec.Promise;

/**
 * Created by thokle on 09/05/2017.
 */
public class SearchService {

	private Session session;

	@Inject
	public SearchService(Session session) {
		this.session = session;
	}

	public Promise<Iterable<WebSite>> searchByArea(SearchParams area) {
		Map<String, Object> map = getStringObjectMap(area);
		return Promise.async(downstream -> {
			Iterable<WebSite> webSites = session.query(WebSite.class,
					"MATCH (w:WebSite)-[:PLACE]->(a:Area) WHERE a.region CONTAINS {region}  OR a.zip CONTAINS {zip} OR a.country = {country} "
							+ "OPTIONAL MATCH (w)-[:DEMOGRAFIC]->(d:Demografi) WHERE d.gender = {gender} AND d.minAge = {minAge} AND d.maxAge = {maxAge} "
							+ " OPTIONAL MATCH (w:WebSite)-[:COOPERATION]->(c:Organisation) WHERE c.type = {type} OR c.category = {category} OR c.visitors = {visitors}  OR c.physicalShop = {physicalShop} RETURN w,c,d ",
					map);
			downstream.success(webSites);
		});
	}

	private Map<String, Object> getStringObjectMap(SearchParams area) {
		Map<String, Object> map = new HashMap<>();
		map.put("country", area.getCountry());
		map.put("zip", area.getZip());
		map.put("region", area.getRegion());
		map.put("category", area.getCategory());
		map.put("gender", area.getGender());
		map.put("minAge", area.getMixAge());
		map.put("maxAge", area.getMaxAge());
		map.put("type", area.getType());
		map.put("visitors", area.getVisitors());
		map.put("physicalShop", area.isPhysicalShop());
		return map;
	}

}
