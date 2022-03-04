package org.ob.api;

import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.cypher.query.Pagination;
import org.neo4j.ogm.session.SessionFactory;
import org.ob.data.model.accounts.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path( "/ob/accounts" )
public class Accounts {

    private static final Logger logger = LoggerFactory.getLogger( Accounts.class );

    @Inject
    SessionFactory sessions;

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public List<Account> fetchAccounts(@Context UriInfo uri, @QueryParam( "open-status" ) String status, @DefaultValue( "1" ) @QueryParam( "page" ) int page, @DefaultValue( "10" ) @QueryParam( "page-size" ) int size){
        var pagination = new Pagination( page - 1, size );
        var filters = new Filters();


        if ( status != null ) {
            filters.and( new Filter( "status", ComparisonOperator.EQUALS,  status ) );
        }
        var accounts = new ArrayList<>(sessions.openSession().loadAll(Account.class, filters, pagination));

        return accounts;
    }
}

