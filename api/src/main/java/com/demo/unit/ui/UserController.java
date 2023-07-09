package com.demo.unit.ui;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.User;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.ui.dto.ChangeEmailRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final IEmailGateway emailGateway;

    @PatchMapping("/{userId}")
    public UserDto patchUser(@PathVariable long userId,
                             @Valid @RequestBody UserPatchRequestDto requestDto) {
        User user = service.findById(userId);
        user.updateName(requestDto.getRename());
        return new UserDto(service.save(user));
    }

    @PostMapping("/greet")
    public boolean greetUser(@Valid @RequestBody GreetEmailRequestDto requestDto) {
        return emailGateway.sendGreetingEmail(requestDto.getEmail());
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestBody ChangeEmailRequestDto request) {
        User user = service.findById(request.getUserId());
        user.changeEmail(request.getMail());
        service.save(user);
        return "OK";
    }

}
