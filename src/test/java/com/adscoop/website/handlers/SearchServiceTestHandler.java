package com.adscoop.website.handlers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.adscoop.website.StartWebsiteServcie;
import com.adscoop.website.utils.RxRule;

import ratpack.http.Status;
import ratpack.test.MainClassApplicationUnderTest;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTestHandler {

	private static final String USER_TOKEN = "user_token";

	@Rule
	public RxRule rxRule = new RxRule();

	@Test
	public void testPrefixUforSearch() {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(StartWebsiteServcie.class)) {
			assertThat("Http request search was ok",
					service.getHttpClient().requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
							.get("search/:zip?/:country?/:region?/:category?/:gender?/:minAge?/:maxAge?/:type?/:visitors?/:physicalShop")
							.getStatus(),
					equalTo(Status.OK));
		}
	}

}
