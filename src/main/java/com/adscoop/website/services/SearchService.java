package com.adscoop.website.services;

import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.entites.WebSite;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;
import ratpack.http.HttpUrlBuilder;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by thokle on 09/05/2017.
 */
public class SearchService {


    private Session session;


    @Inject
    public SearchService(Session session) {
        this.session = session;
    }



    public Promise<Iterable<WebSite>> searchByArea(SearchParams area){

        Map<String,String> map = new HashMap();
        map.put("country", Optional.of(area.getCountry()).orElse(""));
        map.put("zip",area.getZip());
        map.put("region",area.getRegion());

        return Promise.async( downstream -> {
            Iterable<WebSite> webSites = session.query(WebSite.class,"MATCH (w:WebSite)-[:PLACE]->(a:Area) WHERE a.region CONTAINS {region}  OR a.zip CONTAINS {zip} OR a.country = {country} " +
                    "OPTIONAL MATCH (w)-[:DEMOGRAFIC]->(d:Demografi) WHERE d.gender = {gender} AND d.minAge = {minAge} AND d.maxAge = {maxAge} " +
                    " OPTIONAL MATCH (w:WebSite)-[:COOPERATION]->(c:Organisation) WHERE c.type = {type} OR c.category = {category} OR c.visitors = {visitors}  OR c.physicalShop = {physicalShop} RETURN w,c,d ",map);
            downstream.success(webSites);

            downstream.complete();
        });


    }

}
