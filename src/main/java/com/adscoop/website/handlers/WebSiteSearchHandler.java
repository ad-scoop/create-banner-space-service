package com.adscoop.website.handlers;

import com.adscoop.website.entites.SearchParams;
import com.adscoop.website.services.SearchService;
import ratpack.handling.Context;
import ratpack.rx.RxRatpack;
import ratpack.util.MultiValueMap;

import javax.inject.Inject;

import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 09/05/2017.
 */
public class WebSiteSearchHandler extends AbstractTokenHandler {

	private SearchService searchService;

	@Inject
	public WebSiteSearchHandler(SearchService searchService) {
		this.searchService = searchService;
	}

	@Override
	protected void handleWithToken(Context ctx, String token) {
		MultiValueMap<String, String> map = ctx.getRequest().getQueryParams();
		RxRatpack.observe(searchService.search(params(map))).forEach(webSites -> ctx.render(json(webSites)));
	}

	private SearchParams params(MultiValueMap<String, String> map) {
		SearchParams.SearchParamsBuilder searchParams = SearchParams.builder();

		map.forEach((s, s2) -> {
			switch (s) {
			case "zip":
				searchParams.zip(s2);
				break;
			case "country":
				searchParams.country(s2);
				break;
			case "region":
				searchParams.region(s2);
				break;
			case "visitors":
				searchParams.visitors(s2);
				break;
			case "category":
				searchParams.category(s2);
				break;
			case "type":
				searchParams.type(s2);
				break;
			case "gender":
				searchParams.gender(s2);
				break;
			case "minAge":
				searchParams.mixAge(Integer.valueOf(s2));
				break;
			case "maxAge":
				searchParams.maxAge(Integer.valueOf(s2));
			case "":
				searchParams.physicalShop(Boolean.valueOf(s2));
			}
		});
		return searchParams.build();
	}
}
