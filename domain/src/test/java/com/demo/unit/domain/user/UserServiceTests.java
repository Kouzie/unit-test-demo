package com.demo.unit.domain.user;

import com.demo.unit.domain.company.Company;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserServiceTests {

    @Test
    void changing_email_from_non_corporate_to_corporate() {
        Company company = new Company("mycorp.com", 1);
        User sut = new User("user@gmail.com", UserType.CUSTOMER, "demo-user", company);

        sut.changeEmail("new@mycorp.com");

        Assertions.assertEquals(2, company.getNumberOfEmployees());
        Assertions.assertEquals("new@mycorp.com", sut.getEmail());
        Assertions.assertEquals(UserType.EMPLOYEE, sut.getType());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "mycorp.com, email@mycorp.com, true",
            "mycorp.com, email@gmail.com, false",
    })
    void changing_email_from_non_corporate_to_corporate(
            String domain, String email, boolean expectedResult
    ) {
        Company sut = new Company(domain, 1);

        boolean result = sut.isEmailCorporate(email);

        Assertions.assertEquals(expectedResult, result);
    }
}
