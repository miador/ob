package org.ob.data;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.SessionFactory;
import org.ob.data.model.*;
import org.ob.data.model.accounts.Account;

public class GraphGenerator {

    private static SessionFactory sessions;

    public static void generate(int A, int T, int C ) {

        var customer = CustomerGenerator.generate();
        var contacts = ContactGenerator.generate(C);
        var accounts = AccountGenerator.generate(A);

        for ( Account a: accounts ) {
            a.setTransactions(TransactionGenerator.generate(a, 5));
            a.setPayments(PaymentGenerator.generate(a, 3));
        }

        customer.setContacts(contacts);
        customer.setAccounts(accounts);

        sessions.openSession().save( customer );

    }

    public static void init( String uri, String username, String password ) {

        sessions = new SessionFactory(
                new Configuration.Builder()
                .uri( uri )
                .useNativeTypes()
                .credentials( username, password )
                .autoIndex( "assert" )
                .build(), "org.ob.data.model" );
    }
}
