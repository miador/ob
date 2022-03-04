package org.ob.data.model.accounts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.balances.Balance;
import org.ob.data.model.customers.Customer;
import org.ob.data.model.payments.Payment;
import org.ob.data.model.transactions.Transaction;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NodeEntity
public class Account {

    @Id
    @GeneratedValue(strategy = UuidStrategy.class)
    private String id;

    @Property
    private String ibanNumber;

    @Index
    private String reference;

    @Property
    private String type;

    @Property
    private String status;

    @Index
    private String currency;

    @Property
    private String description;

    @Index
    private String nickname;

    @Index
    private OffsetDateTime updated;

    @Property
    private OffsetDateTime opened;

    @Relationship(type = "HAS_ACCESS_TO")
    private Balance balance;

    @Relationship( type = "IS_TRANSACTED_FROM", direction = Relationship.INCOMING )
    private List<Transaction> transactions;

    @Relationship( type = "IS_PAYMENT_BY", direction = Relationship.INCOMING )
    private List<Payment> payments;

    public Account() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public OffsetDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(OffsetDateTime updated) {
        this.updated = updated;
    }

    public OffsetDateTime getOpened() {
        return opened;
    }

    public void setOpened(OffsetDateTime opened) {
        this.opened = opened;
    }

    public Balance getBalance() {
        return balance;
    }

    public String getIbanNumber() {
        return ibanNumber;
    }

    public void setIbanNumber(String ibanNumber) {
        this.ibanNumber = ibanNumber;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }

    public static class Builder {

        private String id;
        private String ibanNumber;
        private String reference;
        private String type;
        private String status;
        private OffsetDateTime opened;
        private OffsetDateTime updated;
        private String currency;
        private String description;
        private String nickname;
        private Balance balance;
        private List<Transaction> transactions;
        private List<Payment> payments;

        public Account.Builder id(String id) {
            this.id = id;
            return this;
        }

        public Account.Builder reference(String reference) {
            this.reference = reference;
            return this;
        }

        public Account.Builder status(String status) {
            this.status = status;
            return this;
        }

        public Account.Builder updated(OffsetDateTime updated) {
            this.updated = updated;
            return this;
        }

        public Account.Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Account.Builder type(String type) {
            this.type = type;
            return this;
        }

        public Account.Builder description(String description) {
            this.description = description;
            return this;
        }

        public Account.Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Account.Builder opened(OffsetDateTime opened) {
            this.opened = opened;
            return this;
        }

        public Account.Builder balance(Balance balance) {
            this.balance = balance;
            return this;
        }

        public Account.Builder ibanNumber(String ibanNumber) {
            this.ibanNumber = ibanNumber;
            return this;
        }

        public Account.Builder transaction( Transaction transaction ) {
            if ( this.transactions == null ) {
                this.transactions = new ArrayList<>();
            }
            this.transactions.add( transaction );
            return this;
        }

        public Account.Builder transactions( List<Transaction> transactions ) {
            this.transactions = transactions;
            return this;
        }

        public Account.Builder payment( Payment payment ) {
            if ( payments == null ) {
                payments = new ArrayList<>();
            }
            payments.add( payment );
            return this;
        }

        public Account build() {

            Account a = new Account();
            a.setId(id);
            a.setReference(reference);
            a.setType(type);
            a.setCurrency(currency);
            a.setDescription(description);
            a.setNickname(nickname);
            a.setOpened(opened);
            a.setStatus(status);
            a.setUpdated(updated);
            a.setBalance(balance);
            a.setIbanNumber(ibanNumber);
            a.setTransactions( transactions );
            a.setPayments(payments);

            return a;
        }

    }



}

