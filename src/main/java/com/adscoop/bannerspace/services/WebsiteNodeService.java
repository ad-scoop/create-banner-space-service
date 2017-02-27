package com.adscoop.bannerspace.services;

import com.adscoop.bannerspace.entites.Category;
import com.adscoop.bannerspace.entites.TargetGroups;
import com.adscoop.bannerspace.entites.WebSiteNode;

import java.util.List;
import java.util.Optional;

/**
 * Created by thokle on 25/02/2017.
 */
public interface WebsiteNodeService {
    void save(com.adscoop.bannerspace.entites.WebSiteNode webSiteNode);

    Optional<WebSiteNode> findByHostName(String path) throws Exception;

    Optional<WebSiteNode> findByCompanyName(String companyName);

    Optional<Iterable<WebSiteNode>> finByCriteria(List<Category> categoryList, List<TargetGroups> targetGroups) throws Exception;

    Optional<WebSiteNode> findWebSiteByUserToken(String token) throws Exception;
}
