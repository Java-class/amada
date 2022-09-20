package ir.javaclass.amada.controller;

import ir.javaclass.amada.exception.TokenNotFoundException;
import ir.javaclass.amada.exception.UserAlreadyExistException;
import ir.javaclass.amada.model.UserLoginRequestDto;
import ir.javaclass.amada.model.UserSignUpRequestDto;
import ir.javaclass.amada.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mostafa Anbarmoo
 * Project: amada
 * Date: 2022-09-17
 * Time: 20:54
 */
@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/signUp")
    public void signUp(@RequestBody UserSignUpRequestDto requestDto) throws UserAlreadyExistException {
        userService.signUp(requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail(),
                requestDto.getFirstName(),
                requestDto.getLastName());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Void> login(@RequestBody UserLoginRequestDto requestDto) throws TokenNotFoundException {
        return ResponseEntity
                .ok()
                .header("Authorization",
                        userService.login(requestDto.getUsername(),
                                requestDto.getPassword()))
                .build();
    }
}
