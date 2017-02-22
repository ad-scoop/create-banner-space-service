package com.adscoop.bannerspace.handlers.website;

import com.adscoop.bannerspace.nodeServices.CompanyService;
import com.adscoop.entiites.Company;
import com.adscoop.entiites.WebSiteNode;
import com.google.inject.Inject;


import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.fromJson;
import static ratpack.jackson.Jackson.json;

/**
 * Created by thokle on 15/11/2016.
 */
public class CreateWebSiteHandler implements Handler {

    CompanyService companyService;

    @Inject
    public CreateWebSiteHandler(CompanyService companyService) {
        this.companyService = companyService;

    }

    @Override
    public void handle(Context ctx) throws Exception {
        ctx.parse(fromJson(WebSiteNode.class)).then(webSiteNode ->  {
            String token = ctx.getRequest().getHeaders().get("token");
            String companyname = ctx.getPathTokens().get("companyname");
            if(token != null && companyname !=null){
                Company company =  companyService.findByUserToken(token,companyname);
                WebSiteNode webSiteNode1 = new WebSiteNode();
                webSiteNode1.setHostname(webSiteNode.getHostname());
                webSiteNode1.setPath(webSiteNode.getPath());
                webSiteNode1.setPort(webSiteNode.getPort());
                company.getWebSiteNodes().add(webSiteNode1);


                companyService.save(company);
                ctx.render(json(webSiteNode1));
            }




        });
    }


}
