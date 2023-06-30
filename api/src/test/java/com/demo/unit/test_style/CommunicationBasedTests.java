package com.demo.unit.test_style;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.ui.UserController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommunicationBasedTests {

    @Test
    void sending_a_greeting_mail() {
        IEmailGateway iEmailGateway = Mockito.mock(IEmailGateway.class);
        UserService service = Mockito.mock(UserService.class);
        Mockito.when(iEmailGateway.sendGreetingEmail("kgy1996@naver.com")).thenReturn(true);
        UserController controller = new UserController(service, iEmailGateway);

        boolean result = controller.greetUser("kgy1996@naver.com");

        Mockito.verify(iEmailGateway, Mockito.times(1))
                .sendGreetingEmail("kgy1996@naver.com");
        Assertions.assertTrue(true);
    }
}
