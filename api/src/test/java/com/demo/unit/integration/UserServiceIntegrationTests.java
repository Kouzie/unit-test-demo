package com.demo.unit.integration;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.company.Company;
import com.demo.unit.domain.company.CompanyRepository;
import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserRepository;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.domain.user.UserType;
import com.demo.unit.ui.UserController;
import com.demo.unit.ui.dto.ChangeEmailRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

// jpa test with h2db
@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class UserServiceIntegrationTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    void changing_email_from_corporate_to_non_corporate() {
        Company company = createCompany("mycorp.com", 1);
        UserEntity user = createUser("user@mycorp.com", UserType.EMPLOYEE, company);
        ChangeEmailRequestDto requestDto = new ChangeEmailRequestDto(user.getUserId(), "new@gmail.com");
        UserService userService = new UserService(userRepository, companyRepository);
        IEmailGateway iEmailGateway = Mockito.mock(IEmailGateway.class);

        UserController sut = new UserController(userService, iEmailGateway);
        String result = sut.changeEmail(requestDto);

        Assertions.assertEquals("OK", result);

        UserEntity userFromDb = userRepository.findById(user.getUserId()).orElseThrow();
        UserExtensions userExtensions = new UserExtensions();
        userExtensions
                .shouldExist(userFromDb)
                .withEmail(userFromDb, "new@gmail.com")
                .withType(userFromDb, UserType.CUSTOMER);

        Company companyFromDb = companyRepository.findById(company.getCompanyDomainName()).orElseThrow();
        Assertions.assertEquals(0, companyFromDb.getNumberOfEmployees());
    }

    private UserEntity createUser(String email, UserType type, Company company) {
        UserEntity user = new UserEntity(email, type, "test-user", company);
        user = userRepository.save(user);
        return user;
    }

    private Company createCompany(String domainName, int numberOfEmployees) {
        Company company = new Company(domainName, numberOfEmployees);
        company = companyRepository.save(company);
        return company;
    }

    public class UserExtensions {
        public UserExtensions shouldExist(UserEntity user) {
            Assertions.assertNotNull(user);
            return this;
        }

        public UserExtensions withType(UserEntity user, UserType userType) {
            Assertions.assertEquals(userType, user.getType());
            return this;
        }

        public UserExtensions withEmail(UserEntity user, String email) {
            Assertions.assertEquals(email, user.getEmail());
            return this;
        }
    }
}
