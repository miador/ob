package org.ob.data.model;

import com.github.javafaker.Faker;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.transactions.Transaction;

import java.time.OffsetDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TransactionGenerator {

    public static List<Transaction> generate(Account a, int n ) {

        var transactions = new ArrayList<Transaction>();

        for ( int i = 0; i < n; i++ ) {
            var reference = Faker.instance().commerce().promotionCode();
            var booked = OffsetDateTime.now().minus( Period.ofDays( ( new Random().nextInt( 365 ) ) ) );
            var valued = booked.minus( Period.ofDays( 1 ) );
            var amount = String.valueOf( ( Math.random() * 500.0 ) - 250.0 );
            //var status = Arrays.asList("PENDING", "DECLINED", "COMPLETED").get(new Random().nextInt(2));
            var status = Transaction.TransactionStatus.getRandomStatus();
            var info = Faker.instance().book().publisher();
            var code = Faker.instance().commerce().promotionCode();
            var from = Arrays.asList(a, new Account.Builder().build()).get(new Random().nextInt(1));
            var to = from == a ? new Account.Builder().build() : a;

            var transaction = new Transaction.Builder()
                    .reference(reference)
                    .booked(booked)
                    .valued(valued)
                    .amount(amount, Faker.instance().currency().code())
                    .status(status)
                    .info(info)
                    .code(code)
                    .from(from)
                    .to(to)
                    .build();

            transactions.add(transaction);
        }

        return transactions;
    }

}
