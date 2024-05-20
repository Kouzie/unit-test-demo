package com.demo.unit.ui;

import com.demo.unit.adaptor.impl.EmailGateway;
import com.demo.unit.domain.company.Company;
import com.demo.unit.domain.company.CompanyService;
import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.domain.user.UserType;
import com.demo.unit.ui.dto.UserDto;
import com.demo.unit.ui.dto.UserPatchRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTests {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    UserService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rename_user() throws Exception {
        UserEntity user = createUser();
        when(service.findById(1l)).thenReturn(user);
        when(service.save(user)).thenReturn(user);
        UserPatchRequestDto requestDto = new UserPatchRequestDto();
        requestDto.setRename("rename");

        MvcResult sut = mockMvc.perform(patch("/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto))
                )
                .andExpect(status().isOk())
                .andReturn();

        UserDto sutUser = objectMapper.readValue(sut.getResponse().getContentAsString(), UserDto.class);
        Assertions.assertEquals(user.getName(), sutUser.getName());
    }

    @InjectMocks
    CompanyService companyService;

    @MockBean
    EmailGateway emailGateway;



    @Test
    void mockTest() {

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
