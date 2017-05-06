package com.adscoop.website.services;


import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.WebSite;

import com.google.inject.Inject;
import lombok.Setter;
import org.neo4j.ogm.cypher.ComparisonOperator;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import ratpack.exec.Promise;

/**
 * Created by thokle on 25/02/2017.
 */

public class WebsiteService {

	private Session session;

	@Setter
	private String query;

	@Inject
	public WebsiteService(Session session) {
		this.session = session;
	}

	public void save(WebSite webSite) {
		session.save(webSite);
		session.clear();
	}

	public Promise<Boolean> urlNameExists(String url) {
		return Promise.value(findSingelByUrl(url).isNotEmpty());
	}

	public Promise<Iterable<WebSite>> findByToken(String token) {
		return Promise.value(session.loadAll(WebSite.class, new Filter("token", token)));
	}

	public void delete(long id) {
		this.findById(id).then(w -> {
			session.delete(w);
			session.clear();
		});
	}

	public Promise<Iterable<WebSite>> findByNames(List<String> names) {
		return Promise.value(session.query(WebSite.class, "MATCH (w:WebSite) WHERE w.url IN {names} RETURN w ",
				Collections.singletonMap("names", names)));
	}

	public Promise<Iterable<WebSite>> findWebSiteByRegion(Area region) {
		Map<String, String> stringStringMap = Collections.emptyMap();
		stringStringMap.put("zip", region.getZip());
		stringStringMap.put("country", region.getCountry());
		stringStringMap.put("region", region.getRegion());
		return Promise.value(session.query(WebSite.class,
				"match (w:WebSite)-[WEBSITE_HAS_REGIONS]->(r:Region) where r.region =~{region} or r.city =~ {name} or r.country =~ {country} return w,r",
				stringStringMap));
	}

	public Promise<WebSite> findById(long id) {
		return Promise.value(this.session.load(WebSite.class, id));
	}
	
	private WebSite findSingelByUrl(String url) {
		Collection<WebSite> all = session.loadAll(WebSite.class, new Filter("url", url));
    	if (all.isEmpty()) {
    		return WebSite.builder().build();
    	} else {
    		return all.iterator().next();
    	}
	}



    public  Promise<Iterable<WebSite>> findByHostName(String host){
        Filter filter = new Filter("url",host);
        filter.setComparisonOperator(ComparisonOperator.CONTAINING);

        return Promise.value(session.loadAll(WebSite.class,filter));


    }


    public  Promise<Iterable<WebSite>> findByUrls(List<String> s){

        String param = queryBuilder("w","url",s,ComparisonOperator.CONTAINING);
        return  Promise.value(session.query(WebSite.class,"match (w:WebSite) where {param} return w",Collections.singletonMap("param",param)));


    }

    public String queryBuilder(String prefix, String property ,List<String> names, ComparisonOperator comparisonOperator){
    final StringBuilder stringBuilder =new StringBuilder();
        names.stream().forEach( s -> {
            String query  = prefix+"."+property+"  "+comparisonOperator +"  \""+ s  +"\"  OR " ;
            stringBuilder.append(query);
        });

         return stringBuilder.toString().substring(0,stringBuilder.length()-3);
    }




}
