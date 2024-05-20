package com.demo.unit;

import com.demo.unit.domain.company.Company;
import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserRepository;
import com.demo.unit.domain.user.UserType;
import com.demo.unit.ui.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerEndTests extends MysqlTestContainer {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository repository;

    @Autowired
    MockMvc mockMvc;

    @Test
    void patch_user_end_to_end() throws Exception {
        UserEntity user = createUser();
        user = repository.save(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}", user.getUserId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserDto sutUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);

        Assertions.assertEquals(user.getUserId(), sutUser.getUserId());
        Assertions.assertEquals(user.getName(), sutUser.getName());
    }

    private UserEntity createUser() {
        String email = "test@test.com";
        UserType type = UserType.CUSTOMER;
        String name = "testName";
        Company company = new Company("comp.com", 10);
        UserEntity user = new UserEntity(email, type, name, company);
        return user;
    }
}
