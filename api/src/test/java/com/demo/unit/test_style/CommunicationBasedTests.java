package com.demo.unit.test_style;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.ui.UserController;
import com.demo.unit.ui.dto.GreetEmailRequestDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CommunicationBasedTests {

    @Test
    void sending_a_greeting_mail() {
        IEmailGateway iEmailGateway = Mockito.mock(IEmailGateway.class);
        UserService service = Mockito.mock(UserService.class);
        Mockito.when(iEmailGateway.sendGreetingEmail("kgy1996@naver.com")).thenReturn(true);
        UserController controller = new UserController(service, iEmailGateway);
        GreetEmailRequestDto request = new GreetEmailRequestDto();
        request.setEmail("test@test.com");

        boolean sut = controller.greetUser(request);

        Mockito.verify(iEmailGateway, Mockito.times(1))
                .sendGreetingEmail("kgy1996@naver.com");
    }
}
