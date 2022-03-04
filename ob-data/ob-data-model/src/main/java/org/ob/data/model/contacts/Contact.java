package org.ob.data.model.contacts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.addresses.Address;
import org.ob.data.model.customers.Customer;

import java.util.Objects;

@NodeEntity
public class Contact {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Property
    private String type;

    @Index
    private String reference;

    @Index
    private String name;

    @Property
    private String email;

    @Index
    private String phone;

    @Relationship( type = "IS_ADDRESSED_OF" )
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addresses) {
        this.address = addresses;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Contact contact = ( Contact ) o;
        return Objects.equals( getId(), contact.getId() );
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
        private String type;
        private String reference;
        private String name;
        private String email;
        private String phone;
        private Address address;

        public Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Builder type( String type ) {
            this.type = type;
            return this;
        }

        public Builder ref( String ref ) {
            this.reference = ref;
            return this;
        }

        public Builder name( String name ) {
            this.name = name;
            return this;
        }

        public Builder email( String email ) {
            this.email = email;
            return this;
        }

        public Builder phone( String phone ) {
            this.phone = phone;
            return this;
        }

        public Builder address(Address address ) {
            this.address = address;
            return this;
        }

        public Contact build() {

            Contact c = new Contact();
            c.setId( id );
            c.setType( type );
            c.setReference(reference);
            c.setName( name );
            c.setEmail( email );
            c.setPhone( phone );
            c.setAddress( address );

            return c;
        }

    }

}
