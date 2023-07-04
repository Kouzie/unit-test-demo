package com.demo.unit.service;

import com.demo.unit.MysqlTestContainer;
import com.demo.unit.domain.customer.Customer;
import com.demo.unit.domain.customer.CustomerRepository;
import com.demo.unit.domain.customer.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiceTests extends MysqlTestContainer {
    private static CustomerService service;

    @BeforeAll
    static void beforeAll(@Autowired CustomerRepository repository) {
        service = new CustomerService(repository);
    }

    @Test
    void CRUD() {
        Customer customer = new Customer("hello customer");
        customer = service.save(customer);
        Long customerId = customer.getCustomerId();

        Customer sut = service.findById(customerId);
        Assertions.assertEquals(customerId, sut.getCustomerId());
    }
}
