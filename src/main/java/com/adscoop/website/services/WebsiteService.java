package com.adscoop.website.services;

import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.WebSite;
import com.adscoop.website.operators.ComparisonOperators;
import com.google.inject.Inject;
import lombok.Setter;
import org.neo4j.ogm.session.Session;
import ratpack.exec.Promise;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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

        return Promise.async(downstream -> {
            Iterable<WebSite> webSites = session.query(WebSite.class, "MATCH (w:Website) where w.token={token} return w ", Collections.singletonMap("token", token));
            downstream.success(webSites);
        });

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
        stringStringMap.put("city", region.getCity());
        stringStringMap.put("country", region.getCountry());
        stringStringMap.put("region", region.getRegion());
        return Promise.value(session.query(WebSite.class,
                "match (w:WebSite)-[PLACES]->(r:Area) where r.region =~{region} or r.city =~ {city} or r.country =~ {country} return w,r",
                stringStringMap));
    }

    public Promise<WebSite> findById(long id) {
        return Promise.async(downstream -> {
            WebSite webSitePromise = session.queryForObject(WebSite.class, "match (w:WebSite) where ID(w) = {id} return w", Collections.singletonMap("id", id));
            downstream.success(webSitePromise);


        });
    }

    public Promise<Iterable<WebSite>> findByUrls(List<String> s) {

        String param = queryBuilder("w", "url", s, ComparisonOperators.CONTAINS);
        return Promise.value(
                session.query(WebSite.class, "match (w:WebSite) where " + param + " return w", Collections.emptyMap()));
    }

    public String queryBuilder(String prefix, String property, List<String> names,
                               ComparisonOperators comparisonOperator) {
        final StringBuilder stringBuilder = new StringBuilder();

        names.stream().forEach(s -> {
            if (!s.equalsIgnoreCase("DROP")) {
                String query = prefix + "." + property + "  " + comparisonOperator + "  '" + s + "' OR ";
                stringBuilder.append(query);
            }
        });
        return stringBuilder.toString().substring(0, stringBuilder.length() - 4);
    }

    private WebSite findSingelByUrl(String url) {
        return session.queryForObject(WebSite.class, "match (w:WebSite) where w.url = {url}  return w", Collections.singletonMap("url", url));

    }

}
