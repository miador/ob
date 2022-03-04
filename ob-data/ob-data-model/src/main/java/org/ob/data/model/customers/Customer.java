package org.ob.data.model.customers;

import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.addresses.Address;
import org.ob.data.model.contacts.Contact;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Customer {

    @Id
    @GeneratedValue( strategy = UuidStrategy.class )
    public String id;

    @Index
    private String reference;

    @Property
    private String type;

    @Index
    private String name;

    @Index
    private String email;

    @Index
    private String phone;

    @Property
    private String mobile;

    @Relationship( type = "IS_ADDRESSED_OF" )
    private Address address;

    @Relationship( type = "IS_OWNED_BY", direction = Relationship.INCOMING )
    private List<Account> accounts;

    @Relationship( type = "IS_CONTACT_BY", direction = Relationship.INCOMING )
    private List<Contact> contacts;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public static class Builder {

        private String id;
        private String reference;
        private String type;
        private String name;
        private String email;
        private String phone;
        private String mobile;
        private Address address;
        private List<Account> accounts;
        private List<Contact> contacts;

        public Customer.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Customer.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Customer.Builder type( String type ) {
            this.type = type;
            return this;
        }

        public Customer.Builder name( String name ) {
            this.name = name;
            return this;
        }

        public Customer.Builder email( String email ) {
            this.email = email;
            return this;
        }

        public Customer.Builder phone( String phone ) {
            this.phone = phone;
            return this;
        }

        public Customer.Builder mobile( String mobile ) {
            this.mobile = mobile;
            return this;
        }

        public Customer.Builder address( Address address ) {
            this.address = address;
            return this;
        }

        public Customer.Builder account( Account account ) {
            if ( accounts == null ) {
                accounts = new ArrayList<>();
            }
            accounts.add( account );
            return this;
        }

        public Customer.Builder contact( Contact contact ) {
            if ( contacts == null ) {
                contacts = new ArrayList<>();
            }
            contacts.add( contact );
            return this;
        }

        public Customer build() {

            Customer c = new Customer();
            c.setId( id );
            c.setReference(reference);
            c.setAddress( address );
            c.setEmail( email );
            c.setMobile( mobile );
            c.setName( name );
            c.setPhone( phone );
            c.setType( type );
            c.setAccounts(accounts);
            c.setContacts(contacts);

            return c;

        }

    }

}
