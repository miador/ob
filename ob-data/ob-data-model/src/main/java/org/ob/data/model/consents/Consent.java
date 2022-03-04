package org.ob.data.model.consents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.customers.Customer;
import org.ob.data.model.tokens.Token;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NodeEntity
public class Consent {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Index
    private String reference;

    @Index
    private ConsentStatus status;

    @Property
    private List<Permission> permissions;

    @Index
    private OffsetDateTime created;

    @Property
    private OffsetDateTime updated;

    @Property
    private OffsetDateTime expired;

    @Property
    private OffsetDateTime started;

    @Property
    private OffsetDateTime ended;

    @Relationship( type = "IS_CONSENT_BY" )
    private Customer customer;

    @Relationship( type = "IS_ISSUED_TO" )
    private Account account;

    @Relationship( type = "IS_INTRODUCED_IN" )
    private Token token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public ConsentStatus getStatus() {
        return status;
    }

    public void setStatus(ConsentStatus status) {
        this.status = status;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public OffsetDateTime getExpired() {
        return expired;
    }

    public void setExpired(OffsetDateTime expired) {
        this.expired = expired;
    }

    public OffsetDateTime getStarted() {
        return started;
    }

    public void setStarted(OffsetDateTime started) {
        this.started = started;
    }

    public OffsetDateTime getEnded() {
        return ended;
    }

    public void setEnded(OffsetDateTime ended) {
        this.ended = ended;
    }

    public Customer getGustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Consent consent = ( Consent ) o;
        return Objects.equals( getId(), consent.getId() );
    }

    @Override
    public int hashCode() {
        return Objects.hash( getId() );
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString( this );
        } catch ( JsonProcessingException e ) {
            return super.toString();
        }
    }

    @NodeEntity
    public enum ConsentStatus {
        AUTHORISED( "Authorised" ),
        AWAITINGAUTHORISATION( "AwaitingAuthorisation" ),
        REJECTED( "Rejected" ),
        REVOKED( "Revoked" );
        private final String value;

        ConsentStatus( String value ) {
            this.value = value;
        }

        public static ConsentStatus fromValue( String value ) {
            for ( ConsentStatus b : ConsentStatus.values() ) {
                if ( b.value.equals( value ) ) {
                    return b;
                }
            }
            throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf( value );
        }

    }

    @NodeEntity
    public enum Permission {

        READACCOUNTSBASIC( "ReadAccountsBasic" ),
        READACCOUNTSDETAIL( "ReadAccountsDetail" ),
        READBALANCES( "ReadBalances" ),
        READCUSTOMER( "ReadCustomer" ),
        READSCHEDULEDPAYMENTSBASIC( "ReadScheduledPaymentsBasic" ),
        READSCHEDULEDPAYMENTSDETAIL( "ReadScheduledPaymentsDetail" ),
        READTRANSACTIONSBASIC( "ReadTransactionsBasic" ),
        READTRANSACTIONSDETAIL( "ReadTransactionsDetail" );

        private final String value;

        Permission( String value ) {
            this.value = value;
        }

        public static Permission fromValue( String value ) {
            for ( Permission b : Permission.values() ) {
                if ( b.value.equals( value ) ) {
                    return b;
                }
            }
            throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf( value );
        }

    }

    public static class Builder {

        private String id;
        private String reference;
        private ConsentStatus status;
        private List<Permission> permissions;
        private OffsetDateTime created;
        private OffsetDateTime updated;
        private OffsetDateTime expired;
        private OffsetDateTime started;
        private OffsetDateTime ended;
        private Customer customer;
        private Account account;
        private Token token;

        public Consent.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Consent.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Consent.Builder status( ConsentStatus status ) {
            this.status = status;
            return this;
        }

        public Consent.Builder permission( Permission permission ) {
            if ( permissions == null ) {
                permissions = new ArrayList<>();
            }
            permissions.add( permission );
            return this;
        }

        public Consent.Builder created( OffsetDateTime created ) {
            this.created = created;
            return this;
        }

        public Consent.Builder updated( OffsetDateTime updated ) {
            this.updated = updated;
            return this;
        }

        public Consent.Builder expired( OffsetDateTime expired ) {
            this.expired = expired;
            return this;
        }

        public Consent.Builder started( OffsetDateTime started ) {
            this.started = started;
            return this;
        }

        public Consent.Builder ended( OffsetDateTime ended ) {
            this.ended = ended;
            return this;
        }

        public Consent.Builder account( Account account ) {
            this.account = account;
            return this;
        }

        public Consent.Builder customer( Customer customer ) {
            this.customer = customer;
            return this;
        }

        public Consent.Builder token( Token token ) {
            this.token = token;
            return this;
        }

        public Consent build() {

            Consent c = new Consent();
            c.setId( id );
            c.setReference(reference);
            c.setStatus( status );
            c.setPermissions( permissions );
            c.setCreated( created );
            c.setUpdated( updated );
            c.setExpired( expired );
            c.setStarted( started );
            c.setEnded( ended );
            c.setCustomer( customer );
            c.setAccount( account );
            c.setToken( token );

            return c;

        }

    }

}
