package org.ob.data.model;

import com.github.javafaker.Faker;
import org.ob.data.model.addresses.Address;
import org.ob.data.model.customers.Customer;

public class CustomerGenerator {

    public static Customer generate(){

        var reference = Faker.instance().commerce().promotionCode();
        var name = Faker.instance().name().firstName();
        var email = Faker.instance().internet().emailAddress();
        var phone = Faker.instance().phoneNumber().phoneNumber();
        var mobile = Faker.instance().phoneNumber().cellPhone();

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

        return new Customer.Builder()
                .reference(reference)
                .address(address)
                .name(name)
                .email(email)
                .mobile(mobile)
                .phone(phone)
                .build();

    }

}
