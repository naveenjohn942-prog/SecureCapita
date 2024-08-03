package io.getarrays.securecapita.controller;

import io.getarrays.securecapita.dto.UserDTO;
import io.getarrays.securecapita.model.HttpResponse;
import io.getarrays.securecapita.model.User;
import io.getarrays.securecapita.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import static java.time.LocalDateTime.now;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint works!");
    }

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody User user) {
        UserDTO userDTO = userService.createUser(user);
        System.out.println(userDTO);
        return ResponseEntity.created(getUri()).body(
                HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("user",userDTO))
                        .message("user created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build());
    }


    private URI getUri(){
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    }
}
