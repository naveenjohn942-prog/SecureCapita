package io.getarrays.securecapita.controller;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.model.HttpResponse;
import io.getarrays.securecapita.model.User;
import io.getarrays.securecapita.form.*;
import io.getarrays.securecapita.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;



    @GetMapping("/")
    public String home() {
        return "Welcome to the home page!";
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
//        UserDTO user = userService.getUserByEmail(loginForm.getEmail());
//        return user.getMfaEnabled() ? sendVerificationCode(user):sendResponse(user);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            UserDTO user = userService.getUserByEmail(loginForm.getEmail());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(HttpResponse.builder()
                                .statusCode(HttpStatus.NOT_FOUND.value())
                                .status(HttpStatus.NOT_FOUND)
                                .message("User not found")
                                .build());
            }

            // Assuming `user.getMfaEnabled()` returns a boolean indicating if MFA is enabled
            return user.getMfaEnabled() ? sendVerificationCode(user) : sendResponse(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(HttpResponse.builder()
                            .statusCode(HttpStatus.FORBIDDEN.value())
                            .status(HttpStatus.FORBIDDEN)
                            .message("Login failed")
                            .developerMessage(e.getMessage())
                            .build());
        }

    }



    @PostMapping("/register")
    public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user){
        UserDTO userDTO = userService.createUser(user);
        System.out.println(userDTO);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user",userDTO))
                        .message("user created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }


    private URI getUri(){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }

    private ResponseEntity<HttpResponse> sendResponse(UserDTO user) {
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user",user))
                        .message("Login successful")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }

    private ResponseEntity<HttpResponse> sendVerificationCode(UserDTO user) {
        userService.sendVerificationCode(user);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(of("user",user))
                        .message("Verification code sent")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build());
    }
}
