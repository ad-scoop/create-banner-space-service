package com.adscoop.bannerspace.nodeServices;

import com.adscoop.entiites.Company;
import com.adscoop.services.neo4j.connection.ConnectionSource;
import com.google.inject.Inject;


import java.util.Collections;

/**
 * Created by thokle on 16/11/2016.
 */
public class CompanyService  {

    ConnectionSource connectionSource;

    @Inject
    public CompanyService(ConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }


    public Company findByUserToken(String token, String companyname){
        return  connectionSource.session().queryForObject(Company.class,"match (u:UserNode)-[:USER_HAS_COMPANY]->(c:Company ) where c.companyname='"+ companyname  +"' and u.token='" + token +"' return c" , Collections.EMPTY_MAP);

    }


    public long save(Company company ){
        connectionSource.session().save(company);
        return  findById(company.getId()).getId();
    }

    public Company findById(long id) {
        return connectionSource.session().load(Company.class,id);
    }
}
