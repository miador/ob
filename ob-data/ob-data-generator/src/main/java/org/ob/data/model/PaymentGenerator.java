package org.ob.data.model;

import com.github.javafaker.Faker;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.payments.Payment;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PaymentGenerator {

    public static List<Payment> generate(Account a, int n ) {

        var payments = new ArrayList<Payment>();

        for ( int i = 0; i < n; i++ ) {
            var from = Arrays.asList(a, new Account.Builder().build()).get(new Random().nextInt(1));
            var to = from == a ? new Account.Builder().build() : a;
            var amount = String.valueOf( ( Math.random() * 500.0 ) - 250.0 );
            var reference = Faker.instance().commerce().promotionCode();
            var schedule = new Payment.Schedule.Builder().start( OffsetDateTime.now() ).period( Period.ofYears( 1 ) ).duration( Duration.ofDays( 30 ) ).build();

            var payment = new Payment.Builder()
                    .amount(amount, Faker.instance().currency().code())
                    .from(from)
                    .to(to)
                    .reference(reference)
                    .schedule(schedule)
                    .build();

            payments.add(payment);
        }

        return payments;
    }
}
