package com.demo.unit.ui;

import com.demo.unit.adaptor.IEmailGateway;
import com.demo.unit.domain.user.UserEntity;
import com.demo.unit.domain.user.UserService;
import com.demo.unit.ui.dto.ChangeEmailRequestDto;
import com.demo.unit.ui.dto.GreetEmailRequestDto;
import com.demo.unit.ui.dto.UserDto;
import com.demo.unit.ui.dto.UserPatchRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;
    private final IEmailGateway emailGateway;

    @GetMapping("/{userId}")
    public UserDto getUser(@PathVariable long userId) {
        UserEntity user = service.findById(userId);
        return new UserDto(user);
    }

    @PatchMapping("/{userId}")
    public UserDto patchUser(@PathVariable long userId,
                             @Valid @RequestBody UserPatchRequestDto requestDto) {
        UserEntity user = service.findById(userId);
        user.updateName(requestDto.getRename());
        return new UserDto(service.save(user));
    }

    @PostMapping("/greet")
    public boolean greetUser(@Valid @RequestBody GreetEmailRequestDto requestDto) {
        return emailGateway.sendGreetingEmail(requestDto.getEmail());
    }

    @PostMapping("/change-email")
    public String changeEmail(@RequestBody ChangeEmailRequestDto request) {
        UserEntity user = service.findById(request.getUserId());
        user.changeEmail(request.getMail());
        service.save(user);
        return "OK";
    }

}
