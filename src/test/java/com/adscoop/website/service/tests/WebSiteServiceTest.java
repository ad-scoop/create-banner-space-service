package com.adscoop.website.service.tests;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.adscoop.website.services.WebsiteServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;
import ratpack.exec.ExecResult;
import ratpack.exec.Promise;
import ratpack.test.exec.ExecHarness;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WebSiteServiceTest {

    private static final String HOST_NAME = "test";

    private static final String TOKEN = "";

	private WebsiteServiceImpl websiteService;

    @Mock
    private Session session;
    
    @Before
    public void setUp() {
        websiteService = new WebsiteServiceImpl(session);
    }

    @Test
    public void verifyThatUsersWebSiteIsReturnded() throws Exception {
        try (ExecHarness execHarness = ExecHarness.harness()) {
        	// given 
            when(session.queryForObject(eq(WebSite.class), anyString(), anyMapOf(String.class, String.class)))
            	.thenReturn(WebSite.builder()
            		.hostname(HOST_NAME)
            		.build());
            
            // when
            ExecResult<WebSite> execResult = execHarness.yield(execution -> websiteService.findByHostName(HOST_NAME));

            // then
            assertEquals(HOST_NAME, execResult.getValue().getHostname());
        }
    }



    @Test
    public void   verifyThatWebsiteIsReturnByTokenAndHostName() throws Exception {
        try(ExecHarness execHarness = ExecHarness.harness()){
        //Given
            when(session.queryForObject(eq(WebSite.class),anyString(),anyMapOf(String.class,String.class)))
                    .thenReturn(WebSite.builder()
                            .hostname(HOST_NAME).
                                    build());
            //When
            ExecResult<WebSite> result  = execHarness.yield(execution -> websiteService.findWebSiteByUserTokenAndHostname(TOKEN,HOST_NAME)) ;
            //then
            assertEquals(HOST_NAME,result.getValue().getHostname());
            verify(session,times(1)).queryForObject(eq(WebSite.class),anyString(),anyMapOf(String.class,String.class));


        }

    }


    @Test
    public  void verifyThatWebSiteISReturnedByFindWebSitesByToken() throws Exception {
        try(ExecHarness execHarness = ExecHarness.harness()){

            when(session.queryForObject(eq(WebSite.class),anyString(),anyMapOf(String.class,String.class)))
                    .thenReturn(WebSite.builder()
                            .hostname(HOST_NAME).
                                    build());

            ExecResult<WebSite> result = execHarness.yield(execution -> websiteService.findByHostName(HOST_NAME));

            assertEquals(HOST_NAME,result.getValue().getHostname());

        }
    }


    }
