package com.adscoop.website.modules;

import com.google.inject.Inject;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.io.IOException;


public class ConnectionSource {


    SessionFactory sessionFactory;


    @Inject
    public ConnectionSource(Config config) throws IOException {

        sessionFactory = new SessionFactory(configuration(config), "com.adscoop.website.entites");

        configuration(config);
    }

    private Configuration configuration(Config config) throws IOException {
        Configuration configuration = new Configuration.Builder().uri(config.getNeo4Url()).connectionPoolSize(30).credentials(config.getUsername(),config.getPassword()).build();

        return configuration;

    }

    public Session session() {

        return sessionFactory.openSession();

    }


}
