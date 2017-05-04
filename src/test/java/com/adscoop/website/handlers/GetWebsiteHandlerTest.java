package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;

import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

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
			.when(session).load(eq(WebSite.class), anyLong());
		
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
