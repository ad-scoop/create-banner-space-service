package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

@RunWith(MockitoJUnitRunner.class)
public class CreateWebSiteHandlerTest {

	private static final String URL = "www.gundmann.dk";

	@Mock
	private Session session;

	private CreateWebSiteHandler handler;
	
	@Before
	public void setUp() {
		handler = new CreateWebSiteHandler(new WebsiteService(session));
	}

	/**
	 *  I'm not sure why this should be testet here
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void verifyThatAcampaginIsCreated() throws Exception {
		// given
		WebSite existingWebSite = WebSite.builder()
				.url(URL)
				.build();
		 when(session.queryForObject(eq(WebSite.class), any(String.class), anyMap())).thenReturn(Lists.newArrayList());
		// when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
					.body(toJson(WebSite.builder().build()), "application/json")
					.header(Const.Headers.TOKEN, "foo"));

		// then
		assertEquals("Website was not saved", Status.OK, result.getStatus());
		verify(session).save(any(WebSite.class));
	}

	@Test
	public void verifyThatAwebsiteIsNotCreatedIfexists() throws Exception {
		// given
		WebSite existingWebSite = WebSite.builder()
				.url(URL)
				.build();

		 when(session.queryForObject(eq(WebSite.class), any(String.class), anyMap())).thenReturn(existingWebSite);
		// when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
					.body(toJson(existingWebSite), "application/json")
					.header(Const.Headers.TOKEN, "foo"));

		// then
		assertEquals("Website was not saved", Status.of(409), result.getStatus());
	}
	
	private String toJson(WebSite webSite) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(webSite);
	}

}
