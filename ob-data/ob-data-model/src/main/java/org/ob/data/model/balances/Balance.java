package org.ob.data.model.balances;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.amounts.Amount;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.Objects;

@NodeEntity
public class Balance {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Index
    private String ref;

    @Property
    private String type;

    @Index
    private OffsetDateTime updated;

    @Relationship( type = "IS_BASED_ON" )
    private Amount amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Balance balance = ( Balance ) o;
        return Objects.equals( getId(), balance.getId() );
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

    public static class Builder {

        private String id;
        private String reference;
        private String type;
        private OffsetDateTime updated;
        private Amount amount;

        public Balance.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Balance.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Balance.Builder type( String type ) {
            this.type = type;
            return this;
        }

        public Balance.Builder updated( OffsetDateTime updated ) {
            this.updated = updated;
            return this;
        }

        public Balance.Builder amount( Amount amount ) {
            this.amount = amount;
            return this;
        }

        public Balance.Builder amount( String amount ) {
            if ( this.amount == null ) {
                this.amount = new Amount();
            }
            this.amount.setValue( new BigDecimal( amount ) );
            return this;
        }

        public Balance.Builder currency( String currency ) {
            if ( this.amount == null ) {
                this.amount = new Amount();
            }
            this.amount.setCurrency( Currency.getInstance( currency ).getCurrencyCode() );
            return this;
        }

        public Balance build() {

            Balance b = new Balance();
            b.setId( id );
            b.setRef(reference);
            b.setAmount( amount );
            b.setUpdated( updated );
            b.setType( type );

            return b;
        }

    }

}
