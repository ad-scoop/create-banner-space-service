package com.adscoop.bannerspace.nodeServices;




import com.adscoop.bannerspace.entites.Company;
import com.google.inject.Inject;
import org.neo4j.ogm.session.Session;


import java.util.Collections;

/**
 * Created by thokle on 16/11/2016.
 */
public class CompanyService  {

    Session connectionSource;

    @Inject
    public CompanyService(Session connectionSource) {
        this.connectionSource = connectionSource;
    }


    public Company findByUserToken(String token, String companyname){
        return  connectionSource.queryForObject(Company.class,"match (u:UserNode)-[:USER_HAS_COMPANY]->(c:Company ) where c.companyname='"+ companyname  +"' and u.token='" + token +"' return c" , Collections.EMPTY_MAP);

    }


    public long save(Company company ){
        connectionSource.save(company);
        return  findById(company.getId()).getId();
    }

    public Company findById(long id) {
        return connectionSource.load(Company.class,id);
    }
}
