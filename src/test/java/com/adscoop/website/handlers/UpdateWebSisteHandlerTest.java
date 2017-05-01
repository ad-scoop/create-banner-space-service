package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.neo4j.ogm.session.Session;

import com.adscoop.website.entites.WebSite;
import com.adscoop.website.services.WebsiteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

@RunWith(MockitoJUnitRunner.class)
public class UpdateWebSisteHandlerTest {

	private static final String URL = "www.gundmann.dk";

	@Mock
	private Session session;

	private UpdateWebSisteHandler handler;
	
	@Before
	public void setUp() {
		handler = new UpdateWebSisteHandler(new WebsiteService(session));
	}

	@Test
	public void verifyThatAWebSiteIsReturnd() throws Exception {
		// given when
		HandlingResult result = RequestFixture.handle(handler,
				fixture -> fixture
					.header(Const.Headers.TOKEN, "foo")
					.body(toJson(WebSite.builder().build()), "application/json")
					.pathBinding(Collections.singletonMap(Const.Parameter.URL, URL)));

		// then
		assertEquals("Website was not found", Status.OK, result.getStatus());
		verify(session).save(any(WebSite.class));
	}

	private String toJson(WebSite webSite) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(webSite);
	}

}
