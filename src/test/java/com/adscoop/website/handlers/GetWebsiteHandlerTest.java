package com.adscoop.website.handlers;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;
import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class GetWebsiteHandlerTest {

    private static final String ID = "22234243";

    @Mock
    private Session session;

    private GetWebsiteHandler handler;

    @Before
    public void setUp() {
        handler = new GetWebsiteHandler(new WebsiteService(session));
    }

    @Test
    public void verifyThatAWebSiteIsReturnd() throws Exception {
        // given
        doReturn(WebSite.builder()
                .build())
                .when(session).queryForObject(eq(WebSite.class), anyString(), anyMap());

        // when
        HandlingResult result = RequestFixture.handle(handler,
                fixture -> fixture
                        .header(Const.Headers.TOKEN, "foo")
                        .pathBinding(Collections.singletonMap(Const.Parameter.ID, ID)));

        // then
        assertEquals("Website was not found", Status.OK, result.getStatus());
    }

    @Test
    public void verifyThatAWebSiteIsNotReturndIfNotFound() throws Exception {
        // given
        doReturn(null)
                .when(session).load(eq(WebSite.class), anyLong());

        // when
        HandlingResult result = RequestFixture.handle(handler,
                fixture -> fixture
                        .header(Const.Headers.TOKEN, "foo")
                        .pathBinding(Collections.singletonMap(Const.Parameter.ID, ID)));

        // then
        assertEquals("should return error", Status.of(404), result.getStatus());
    }

    @Test
    public void givenNoIdParamterWillRetrunError() throws Exception {
        // given when
        HandlingResult result = RequestFixture.handle(handler,
                fixture -> fixture
                        .header(Const.Headers.TOKEN, "foo"));

        // then
        assertEquals("should return error", Status.of(412), result.getStatus());
    }

}
