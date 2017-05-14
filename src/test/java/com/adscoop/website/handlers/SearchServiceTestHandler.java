package com.adscoop.website.handlers;

import com.adscoop.website.StartWebsiteServcie;
import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.SearchService;
import com.adscoop.website.utils.RxRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ratpack.exec.Promise;
import ratpack.http.Status;
import ratpack.test.MainClassApplicationUnderTest;
import ratpack.test.handling.RequestFixture;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by thokle on 11/05/2017.
 */

@RunWith(MockitoJUnitRunner.class )
public class SearchServiceTestHandler {

    private static final String USER_TOKEN = "user_token";

    @Rule
    public RxRule rxRule = new RxRule();

    @Mock
    private SearchService searchService;

    private  WebSiteSearchHandler webSiteSearchHandler;

    @Before
    public void setup(){
        webSiteSearchHandler  = new WebSiteSearchHandler(searchService);

    }

    @Test
    public void testPrefixUforSearch(){
        try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(StartWebsiteServcie.class)) {

            assertThat("Http request status was  ok",
                    service.getHttpClient().requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
                            .get("search/:zip?/:country?/:region?/:category?/:gender?/:minAge?/:maxAge?/:type?/:visitors?/:physicalShop?" ).getStatus(),
                    equalTo(Status.OK));

        }
    }




    }

