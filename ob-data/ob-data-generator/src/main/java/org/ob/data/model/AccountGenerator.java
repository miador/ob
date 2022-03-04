package org.ob.data.model;

import com.github.javafaker.Faker;
import org.ob.data.model.accounts.Account;
import org.ob.data.model.balances.Balance;

import java.time.OffsetDateTime;
import java.time.Period;
import java.util.*;

public class AccountGenerator {

    public static List<Account> generate(int n){

        ArrayList<Account> accounts = new ArrayList<>();

        for ( int i = 0; i < n; i++ ) {

            var opened = OffsetDateTime.now().minus( Period.ofDays( ( new Random().nextInt( 365 ) ) ) );
            var updated = opened.plus(Period.ofDays(new Random().nextInt(365)));
            var currency = Currency.getAvailableCurrencies().iterator().next().getCurrencyCode();
            var ibanNumber = Faker.instance().finance().iban();
            var reference = Faker.instance().commerce().promotionCode();
            var type = Arrays.asList("BUSINESS", "INDIVIDUAL").get(new Random().nextInt(1));
            var status = Arrays.asList("OPEN", "CLOSED", "SUSPENDED").get(new Random().nextInt(2));
            var description = Faker.instance().book().title();
            var nickname = Faker.instance().name().username();

            var balance = new Balance.Builder()
                    .amount(String.valueOf( ( Math.random() * 2000.0 ) - 1000.0 ))
                    .currency(currency)
                    .reference(reference)
                    .updated(updated)
                    .build();

            var account = new Account.Builder()
                    .opened(opened)
                    .updated(updated)
                    .currency(currency)
                    .ibanNumber(ibanNumber)
                    .reference(reference)
                    .type(type)
                    .status(status)
                    .description(description)
                    .nickname(nickname)
                    .balance(balance)
                    .build();

            accounts.add(account);

        }
        return accounts;
    }

}
