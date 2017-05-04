package com.adscoop.website.handlers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
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

import ratpack.http.Status;
import ratpack.test.handling.HandlingResult;
import ratpack.test.handling.RequestFixture;

@RunWith(MockitoJUnitRunner.class)
public class DeleteWebSiteHandlerTest {

	private static final String ID = "3333";

	@Mock
	private Session session;

	private DeleteWebSiteHandler handler;
	
	@Before
	public void setUp() {
		handler = new DeleteWebSiteHandler(new WebsiteService(session));
	}
	
	@Test
	public void verifyThatAWebSiteIsDeleted() throws Exception {
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
		assertEquals("Website was not deleted", Status.OK, result.getStatus());
		verify(session).delete(any(WebSite.class));
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
