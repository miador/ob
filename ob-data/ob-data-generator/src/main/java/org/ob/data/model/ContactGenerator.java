package org.ob.data.model;

import com.github.javafaker.Faker;
import org.ob.data.model.addresses.Address;
import org.ob.data.model.contacts.Contact;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ContactGenerator {
    public static List<Contact> generate(int n) {

        var contacts = new ArrayList<Contact>();
        for ( int i = 0; i < n; i++ ) {
            var type = Arrays.asList("BILL", "SEPA", "INTERNATIONAL").get(new Random().nextInt(2));
            var reference = Faker.instance().commerce().promotionCode();
            var name = Faker.instance().name().firstName() + Faker.instance().name().lastName();
            var email = Faker.instance().internet().emailAddress();
            var phone = Faker.instance().phoneNumber().phoneNumber();

            var fakeAddress = Faker.instance().address();
            var address = new Address.Builder()
                    .country(fakeAddress.country())
                    .city(fakeAddress.cityName())
                    .ref(reference)
                    .postcode(fakeAddress.zipCode())
                    .street(fakeAddress.streetName())
                    .number(fakeAddress.streetAddressNumber())
                    .region(fakeAddress.state())
                    .build();

            var contact = new Contact.Builder()
                    .address(address)
                    .email(email)
                    .name(name)
                    .phone(phone)
                    .ref(reference)
                    .type(type)
                    .build();

            contacts.add(contact);

        }
        return contacts;
    }
}
