package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

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
public class GetWebSitesHandlerTest {

	@Mock
	private Session session;

	private GetWebSitesHandler handler;
	
	@Before
	public void setUp() {
		handler = new GetWebSitesHandler(new WebsiteService(session));
	}
	
	@Test
	public void verifyThatAWebSiteIsReturnd() throws Exception {
		// given
		doReturn(Lists.newArrayList(WebSite.builder()
				.build()))
			.when(session).loadAll(eq(WebSite.class), any(Filter.class));
		
		// when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture.header(Const.Headers.TOKEN, "foo"));

		// then
		assertEquals("Website was not found", Status.OK, result.getStatus());
	}
	
	@Test
	public void givenNoTokenWillRetrunError() throws Exception {
		// given when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture.method("GET"));

		// then
		assertEquals("should return error", result.getStatus(), Status.of(406));
	}

	
}
