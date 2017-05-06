package com.adscoop.website.service.tests;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.session.Session;

import java.util.ArrayList;
import java.util.List;
import static  org.junit.Assert.*;
/**
 * Created by thokle on 06/05/2017.
 */


@RunWith(MockitoJUnitRunner.class)

public class WebSiteServiceTest {

@Mock
private Session session;

    private WebsiteService websiteService;


    @Before
    public void setup(){

        this.websiteService = new WebsiteService(session);

    }

    @Test
    public void testQuesrBuilder(){


        //setup
        List<String> search = new ArrayList<>();
        search.add("hello");
        search.add("world");
        search.add("fart");
        //expected result;
        String exp = "w.url  CONTAINING  \"hello\"  OR w.url  CONTAINING  \"world\"  OR w.url  CONTAINING  \"fart\""  ;


        String res = websiteService.queryBuilder("w","url", search, ComparisonOperator.CONTAINING);

        assertEquals(exp.trim(),res.trim());




    }


    private  WebSite webSite() {
      return   WebSite.builder().path("/path").url("host").build();

    }

}
