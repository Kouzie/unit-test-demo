package com.demo.unit.domain.user;

import com.demo.unit.domain.company.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

@JsonTest
public class UserJsonTests {

    @Autowired
    private JacksonTester<UserEntity> json;

    @BeforeAll
    static void beforeAll(@Autowired ObjectMapper objectMapper) {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    @Test
    void json_serialize_user() throws IOException {
        Company company = new Company("mycorp.com", 1);
        UserEntity user = new UserEntity("user@gmail.com", UserType.CUSTOMER, "demo-user", company);
        JsonContent<UserEntity> userJson = json.write(user);
        System.out.println(userJson.getJson());
        File file = new ClassPathResource("domain/user/User.json").getFile();

        UserEntity sut = json.read(file).getObject();

        Assertions.assertEquals(user.getName(), sut.getName());
        Assertions.assertEquals(user.getEmail(), sut.getEmail());
        Assertions.assertEquals(user.getType(), sut.getType());
        Assertions.assertEquals(user.getCompany().getNumberOfEmployees(), sut.getCompany().getNumberOfEmployees());
    }
}