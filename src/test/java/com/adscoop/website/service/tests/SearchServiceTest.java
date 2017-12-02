package com.adscoop.website.service.tests;

import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.SearchService;

import org.apache.commons.collections4.iterators.IteratorIterable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;
import ratpack.exec.ExecResult;
import ratpack.test.exec.ExecHarness;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Mockito.when;

/**
 * Created by thokle on 15/05/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTest {

    @Mock
    private
    Session session;


    private SearchService searchService;

    @Before
    public void setup() {
        searchService = new SearchService(session);
    }

    @Test
    public void testThatMappingIsCorrect() throws Exception {

        try (ExecHarness execHarness = ExecHarness.harness()) {
            when(session.query(any(), any(), anyMapOf(String.class, String.class))).thenReturn(webSites())  ;
            ExecResult<Iterable<WebSite>> iterableExecResult = execHarness.yield(execution -> searchService.search(searchParams()));


        }

    }

    private Iterable<Object> webSites() {
        Iterator iterator = null;
        List<WebSite> webSiteList = new ArrayList<>();

        return new IteratorIterable<>( webSiteList.iterator()   );

    }


    private SearchParams searchParams() {
        return SearchParams.builder().physicalShop(true).maxAge(100).mixAge(0).gender("male").visitors("2000").type("it").region("region").country("dk").zip("2000").category("cat").build();


    }











}

