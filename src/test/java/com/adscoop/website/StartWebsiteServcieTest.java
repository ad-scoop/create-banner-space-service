package com.adscoop.website;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.adscoop.website.entites.Area;
import com.adscoop.website.entites.BannerSpace;
import com.adscoop.website.entites.Demografi;
import com.adscoop.website.entites.Gender;
import com.adscoop.website.entites.PlaceType;
import com.adscoop.website.entites.WebSite;
import com.adscoop.website.handlers.Const;
import com.adscoop.website.utils.RxRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

import ratpack.http.Status;
import ratpack.test.MainClassApplicationUnderTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StartWebsiteServcieTest {

	private static final String URL = "www.gundmann.dk";
	private static final String USER_TOKEN = "user_token";
	
	@Rule public RxRule rxRule = new RxRule();
	
	@Test
	public void AgivenATokenWillReturnCreateAWebsite() throws Exception {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(
				StartWebsiteServcie.class)) {
		// given when then
		assertThat(
				"Http requst status was no ok", service.getHttpClient()
						.requestSpec(request -> request
								.body(body -> body
										.type("application/json")
										.text(buildWebsite()))
								.getHeaders()
									.add(Const.Headers.TOKEN, USER_TOKEN)
							)
						.post("websites/create")
						.getStatus(),
				equalTo(Status.OK));
		}
	}

	@Test
	public void BgivenATokenWillReturnWebsites() throws Exception {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(
				StartWebsiteServcie.class)) {
		// given when then
		assertThat(
				"Http requst status was no ok", service.getHttpClient()
						.requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
						.get("websites")
						.getStatus(),
				equalTo(Status.OK));
		}
	}

	@Test
	public void CgivenATokenWillReturnAWebsite() throws Exception {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(
				StartWebsiteServcie.class)) {
		// given when then
		assertThat(
				"Http requst status was no ok", service.getHttpClient()
						.requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
						.get("websites/" + URL)
						.getStatus(),
				equalTo(Status.OK));
		}
	}

	@Test
	public void DgivenATokenWillUpdateWebsite() throws Exception {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(
				StartWebsiteServcie.class)) {
		// given
		String json = service.getHttpClient()
			.requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
			.get("websites/" + URL).getBody().getText();
			
		// when then
		assertThat(
				"Http requst status was no ok", service.getHttpClient()
						.requestSpec(request -> request
								.body(body -> body
										.type("application/json")
										.text(json))
								.getHeaders()
									.add(Const.Headers.TOKEN, USER_TOKEN)
							)
						.post("websites/update")
						.getStatus(),
				equalTo(Status.OK));
		}
	}
	
	@Test
	public void EgivenATokenWillDeleteAWebsite() throws Exception {
		try (MainClassApplicationUnderTest service = new MainClassApplicationUnderTest(
				StartWebsiteServcie.class)) {
		// given when then
		assertThat(
				"Http requst status was no ok", service.getHttpClient()
						.requestSpec(r -> r.getHeaders().add(Const.Headers.TOKEN, USER_TOKEN))
						.delete("websites/remove/" + URL)
						.getStatus(),
				equalTo(Status.OK));
		}
	}

	private CharSequence buildWebsite() throws Exception {
		return new ObjectMapper().writeValueAsString(WebSite.builder()
				.bannerSpaceSet(newArrayList(BannerSpace.builder()
						.height(200)
						.width(300)
						.place(PlaceType.Left)
						.left(100)
						.standardSize(true)
						.top(400)
						.build()))
				.url(URL)
				.accepted(true)
				.area(Area.builder()
						.country("dk")
						.region("region")
						.zip("2222")
						.build())
				.demografi(Demografi.builder()
						.maxAge(123)
						.minAge(2)
						.genders(newArrayList(Gender.Man, Gender.Woman))
						.build())
				.build());
	}


}
