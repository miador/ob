package org.ob.data.model.transactions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.amounts.Amount;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.Objects;
import java.util.Random;

@NodeEntity
public class Transaction {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Index
    private String reference;

    @Index
    private OffsetDateTime booked;

    @Index
    private OffsetDateTime valued;

    @Property
    private TransactionStatus status;

    @Property
    private String description;

    @Index
    private String code;

    @Relationship( type = "IS_BASED_ON" )
    private Amount amount;

    @JsonIgnoreProperties
    @Transient
    @Property
    private Account from;

    @Relationship( type = "IS_TRANSACTED_TO" )
    private Account to;

    @Property
    private String info;

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

    public TransactionStatus getStatus() {
        return status;
    }

    public OffsetDateTime getBooked() {
        return booked;
    }

    public void setBooked(OffsetDateTime booked) {
        this.booked = booked;
    }

    public OffsetDateTime getValued() {
        return valued;
    }

    public void setValued(OffsetDateTime valued) {
        this.valued = valued;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Transaction that = ( Transaction ) o;
        return Objects.equals( getId(), that.getId() );
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
    public enum TransactionStatus {
        BOOKED( "Booked" ),
        PENDING( "Pending" ),
        DECLINED("Declined");

        private final String value;

        TransactionStatus( String value ) {
            this.value = value;
        }

        public static TransactionStatus fromValue( String value ) {
            for ( TransactionStatus b : TransactionStatus.values() ) {
                if ( b.value.equals( value ) ) {
                    return b;
                }
            }
            throw new IllegalArgumentException( "Unexpected value '" + value + "'" );
        }

        public static TransactionStatus getRandomStatus() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
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
        private String code;
        private String reference;
        private TransactionStatus status;
        private OffsetDateTime booked;
        private OffsetDateTime valued;
        private String info;
        private Amount amount;
        private Account from;
        private Account to;

        public Transaction.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Transaction.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Transaction.Builder status( TransactionStatus status ) {
            this.status = status;
            return this;
        }

        public Transaction.Builder booked( OffsetDateTime booked ) {
            this.booked = booked;
            return this;
        }

        public Transaction.Builder valued( OffsetDateTime valued ) {
            this.valued = valued;
            return this;
        }

        public Transaction.Builder info( String info ) {
            this.info = info;
            return this;
        }

        public Transaction.Builder amount( Amount amount ) {
            this.amount = amount;
            return this;
        }

        public Transaction.Builder amount( String amount, String currency ) {
            if ( this.amount == null ) {
                this.amount = new Amount();
            }
            this.amount.setValue( new BigDecimal( amount ) );
            this.amount.setCurrency( Currency.getInstance( currency ).getCurrencyCode() );
            return this;
        }

        public Transaction.Builder code( String code ) {
            this.code = code;
            return this;
        }

        public Transaction.Builder from( Account from ) {
            this.from = from;
            return this;
        }

        public Transaction.Builder to( Account to ) {
            this.to = to;
            return this;
        }


        public Transaction build() {

            Transaction t = new Transaction();
            t.setId( id );
            t.setInfo( info );
            t.setReference( reference );
            t.setValued( valued );
            t.setCode( code );
            t.setBooked( booked );
            t.setFrom( from );
            t.setTo( to );
            t.setStatus( status );
            t.setAmount( amount );

            return t;
        }

    }

}
