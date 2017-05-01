package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.google.common.collect.Lists;

import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

@RunWith(MockitoJUnitRunner.class)
public class GetWebsiteHandlerTest {

	private static final String URL = "www.gundmann.dk";

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
		doReturn(Lists.newArrayList(WebSite.builder()
				.url(URL)
				.build()))
			.when(session).loadAll(eq(WebSite.class), any(Filter.class));
		
		// when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
					.header(Const.Headers.TOKEN, "foo")
					.pathBinding(Collections.singletonMap(Const.Parameter.URL, URL)));

		// then
		assertEquals("Website was not found", Status.OK, result.getStatus());
	}

	@Test
	public void verifyThatAWebSiteIsNotReturndIfNotFound() throws Exception {
		// given
		doReturn(Lists.newArrayList())
			.when(session).loadAll(eq(WebSite.class), any(Filter.class));
		
		// when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
				.header(Const.Headers.TOKEN, "foo")
				.pathBinding(Collections.singletonMap(Const.Parameter.URL, URL))
				.uri("website/get"));

		// then
		assertEquals("should return error", Status.of(404), result.getStatus());
	}

	@Test
	public void givenNoIdParamterWillRetrunError() throws Exception {
		// given when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
				.header(Const.Headers.TOKEN, "foo")
				.uri("website/get"));

		// then
		assertEquals("should return error", Status.of(412), result.getStatus());
	}
	
}
