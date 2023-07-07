package com.demo.unit.service;

import com.demo.unit.MysqlTestContainer;
import com.demo.unit.domain.customer.Customer;
import com.demo.unit.domain.customer.CustomerRepository;
import com.demo.unit.domain.customer.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.env.Environment;

import java.util.Arrays;

// in-memory jpa test (with test container)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTests extends MysqlTestContainer {
    private static CustomerService service;

    @Autowired
    Environment environment;

    @BeforeAll
    static void beforeAll(@Autowired CustomerRepository repository) {
        service = new CustomerService(repository);
    }


    @Test
    void create_and_find_customer() {
        System.out.println("profile:" + Arrays.toString(environment.getActiveProfiles()));
        Customer customer = new Customer("hello customer");
        customer = service.save(customer);
        Long customerId = customer.getCustomerId();

        Customer sut = service.findById(customerId);

        Assertions.assertEquals(customerId, sut.getCustomerId());
    }
}
