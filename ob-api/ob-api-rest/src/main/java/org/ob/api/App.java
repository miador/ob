package org.ob.api;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Produces;

public class App {

    @ConfigProperty( name = "ob.neo4j.uri" )
    String uri;
    @ConfigProperty( name = "ob.neo4j.username" )
    String username;
    @ConfigProperty( name = "ob.neo4j.password" )
    String password;
    @ConfigProperty( name = "ob.neo4j.packages" )
    String packages;

    @Produces
    SessionFactory produceSessionFactory() {
        return new SessionFactory( new Configuration.Builder().uri( uri ).credentials( username, password ).useNativeTypes().build(), packages );
    }

}
