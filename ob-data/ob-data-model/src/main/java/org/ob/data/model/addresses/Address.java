package org.ob.data.model.addresses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import java.util.Objects;

@NodeEntity
public class Address {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Index
    private String reference;

    @Index
    private String street;

    @Property
    private String number;

    @Index
    private String postcode;

    @Index
    private String city;

    @Property
    private String region;

    @Property
    private String country;

    public Address(){
    }

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Address address = ( Address ) o;
        return Objects.equals( id, address.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
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
        private String street;
        private String number;
        private String postcode;
        private String city;
        private String region;
        private String country;

        public Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Address.Builder ref( String ref ) {
            this.reference = ref;
            return this;
        }

        public Address.Builder street( String street ) {
            this.street = street;
            return this;
        }

        public Address.Builder number( String number ) {
            this.number = number;
            return this;
        }

        public Address.Builder postcode( String postcode ) {
            this.postcode = postcode;
            return this;
        }

        public Address.Builder city( String city ) {
            this.city = city;
            return this;
        }

        public Address.Builder region( String region ) {
            this.region = region;
            return this;
        }

        public Address.Builder country( String country ) {
            this.country = country;
            return this;
        }

        public Address build() {
            Address a = new Address();
            a.setId( id );
            a.setReference(reference);
            a.setCity( city );
            a.setCountry( country );
            a.setNumber( number );
            a.setPostcode( postcode );
            a.setRegion( region );
            a.setStreet( street );

            return a;
        }

    }

}
