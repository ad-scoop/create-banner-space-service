package com.adscoop.website.service.tests;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.adscoop.website.services.WebsiteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;
import ratpack.exec.ExecResult;
import ratpack.test.exec.ExecHarness;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by thokle on 20/04/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class WebSiteServiceTest {

    WebsiteService websiteService;

    @Mock
    Session session;

    @Test
    public void findByUserName() {

        try (ExecHarness execHarness = ExecHarness.harness()) {
            when(session.queryForObject(WebSite.class, anyString(), any(Map.class))).thenReturn(webSite());
            websiteService = new WebsiteServiceImpl(session);

            ExecResult<WebSite> execResult = execHarness.yield(execution -> websiteService.findByHostName("test"));

            assertEquals("test", execResult.getValue().getHostname());

        } catch (Exception e) {
        	fail(e.getLocalizedMessage());
        }
    }





    private WebSite webSite() {

        WebSite webSite = new WebSite();
        webSite.setHostname("test");
        webSite.setPath("/index");
        webSite.setPort(8080);

        return webSite;

    }

}
