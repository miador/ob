package org.ob.data.model.amounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.id.UuidStrategy;
import java.math.BigDecimal;
import java.util.Objects;

@NodeEntity
public class Amount {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Index
    private String reference;

    @Index
    private BigDecimal value;

    @Index
    private String currency;

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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Amount amount = ( Amount ) o;
        return Objects.equals( getId(), amount.getId() );
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
        private BigDecimal value;
        private String currency;

        public Amount.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Amount.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Amount.Builder value( BigDecimal value ) {
            this.value = value;
            return this;
        }

        public Amount.Builder currency( String currency ) {
            this.currency = currency;
            return this;
        }

        public Amount build() {
            var a = new Amount();
            a.setId( id );
            a.setValue( value );
            a.setCurrency( currency );
            a.setReference(reference);

            return a;
        }

    }

}
