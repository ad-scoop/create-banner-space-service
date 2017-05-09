package com.adscoop.website.handlers;

import com.adscoop.website.services.WebsiteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;

/**
 * Created by thokle on 06/05/2017.
 */


@RunWith(MockitoJUnitRunner.class)
public class WebSiteSearchByUrlHandletTest {

    @Mock
    private Session session;


    @Mock
    WebsiteService websiteService;


    @Test
    public  void hendlerReturnsWebsites(){



    }


}
