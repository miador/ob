package org.ob.data.model.payments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.neo4j.ogm.annotation.*;
import org.neo4j.ogm.id.UuidStrategy;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.amounts.Amount;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.Currency;
import java.util.Objects;

@NodeEntity
public class Payment {

    @Id
    @GeneratedValue( strategy = UuidStrategy.class )
    private String id;

    @Index
    private String reference;

    @Relationship( type = "IS_AMOUNT_TO" )
    private Amount amount;

    @JsonIgnoreProperties
    @Transient
    @Property
    private Account from;

    @Relationship( type = "IS_TRANSACTED_TO" )
    private Account to;

    @Relationship( type = "IS_SCHEDULED_BY", direction = Relationship.INCOMING )
    private Schedule schedule;

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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Payment payment = ( Payment ) o;
        return Objects.equals( getId(), payment.getId() );
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
    public static class Schedule {

        @Id
        @GeneratedValue( strategy = UuidStrategy.class )
        private String id;

        @Property
        private OffsetDateTime start;

        @Property
        private Period period;

        @Property
        private Duration duration;

        public String getId() {
            return id;
        }

        public void setId( String id ) {
            this.id = id;
        }

        public OffsetDateTime getStart() {
            return start;
        }

        public void setStart( OffsetDateTime start ) {
            this.start = start;
        }

        public Period getPeriod() {
            return period;
        }

        public void setPeriod( Period period ) {
            this.period = period;
        }

        public Duration getDuration() {
            return duration;
        }

        public void setDuration( Duration duration ) {
            this.duration = duration;
        }

        public static class Builder {

            private String id;
            private OffsetDateTime start;
            private Period period;
            private Duration duration;

            public Builder id( String id ) {
                this.id = id;
                return this;
            }

            public Builder start( OffsetDateTime start ) {
                this.start = start;
                return this;
            }

            public Builder period( Period period ) {
                this.period = period;
                return this;
            }

            public Builder duration( Duration duration ) {
                this.duration = duration;
                return this;

            }

            public Schedule build() {

                var s = new Schedule();
                s.setId( id );
                s.setStart( start );
                s.setPeriod( period );
                s.setDuration( duration );

                return s;

            }

        }

        @Override
        public boolean equals( Object o ) {
            if ( this == o ) return true;
            if ( o == null || getClass() != o.getClass() ) return false;
            Schedule schedule = ( Schedule ) o;
            return Objects.equals( getId(), schedule.getId() );
        }

        @Override
        public int hashCode() {
            return Objects.hash( getId() );
        }

    }

    public static class Builder {

        private String id;
        private String reference;
        private Amount amount;
        private Account from;
        private Account to;
        private Account account;
        private Schedule schedule;

        public Payment.Builder id( String id ) {
            this.id = id;
            return this;
        }

        public Payment.Builder reference( String reference ) {
            this.reference = reference;
            return this;
        }

        public Payment.Builder amount( Amount amount ) {
            this.amount = amount;
            return this;
        }

        public Payment.Builder amount( String amount, String currency ) {
            if ( this.amount == null ) {
                this.amount = new Amount();
            }
            this.amount.setValue( new BigDecimal( amount ) );
            this.amount.setCurrency( Currency.getInstance( currency ).getCurrencyCode() );
            return this;
        }

        public Payment.Builder from( Account from ) {
            this.from = from;
            return this;
        }

        public Payment.Builder to( Account to ) {
            this.to = to;
            return this;
        }

        public Payment.Builder schedule( Schedule schedule ) {
            this.schedule = schedule;
            return this;
        }

        public Payment build() {

            var p = new Payment();
            p.setId( id );
            p.setReference( reference );
            p.setAmount( amount );
            p.setFrom( from );
            p.setTo( to );
            p.setSchedule( schedule );

            return p;

        }

    }

}
