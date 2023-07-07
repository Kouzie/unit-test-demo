package com.demo.unit.ui;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.User;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.ui.dto.GreetEmailRequestDto;
import com.demo.unit.ui.dto.UserDto;
import com.demo.unit.ui.dto.UserPatchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
