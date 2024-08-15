    package io.getarrays.securecapita.controller;

    import io.getarrays.securecapita.dto.UserDTO;
    import io.getarrays.securecapita.exception.ApiException;
    import io.getarrays.securecapita.form.LoginForm;
    import io.getarrays.securecapita.model.HttpResponse;
    import io.getarrays.securecapita.model.User;
    import io.getarrays.securecapita.model.UserPrincipal;
    import io.getarrays.securecapita.provider.TokenProvider;
    import io.getarrays.securecapita.service.RoleService;
    import io.getarrays.securecapita.service.UserService;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;
    import org.apache.http.auth.AUTH;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

    import java.net.URI;

    import static io.getarrays.securecapita.dtoMapper.UserDTOMapper.toUser;
    import static io.getarrays.securecapita.utils.ExceptionUtils.processError;
    import static java.time.LocalDateTime.now;
    import static java.util.Map.of;
    import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;
    import static org.springframework.http.HttpHeaders.AUTHORIZATION;
    import static org.springframework.http.HttpStatus.NOT_FOUND;
    import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;


    @RestController
    @RequestMapping("/user")
    @RequiredArgsConstructor
    public class UserController {
        private final UserService userService;
        private final AuthenticationManager authenticationManager;
        private final TokenProvider tokenProvider;
        private final RoleService roleService;
        private final HttpServletRequest request;
        private final HttpServletResponse response;
        private static final String TOKEN_PREFIX = "Bearer ";


        @PostMapping("/login")
        public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginForm loginForm) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
            Authentication authentication = authenticate(loginForm.getEmail(), loginForm.getPassword());
            UserDTO user = getAuthenticatedUser(authentication);
            return user.getMfaEnabled() ? sendVerificationCode(user):sendResponse(user);
    //        try {
    //            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
    //            UserDTO user = userService.getUserByEmail(loginForm.getEmail());
    //
    //            if (user == null) {
    //                return ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                        .body(HttpResponse.builder()
    //                                .statusCode(HttpStatus.NOT_FOUND.value())
    //                                .status(HttpStatus.NOT_FOUND)
    //                                .message("User not found")
    //                                .build());
    //            }
    //
    //            // Assuming `user.getMfaEnabled()` returns a boolean indicating if MFA is enabled
    //            return user.getMfaEnabled() ? sendVerificationCode(user) : sendResponse(user);
    //
    //        } catch (Exception e) {
    //            return ResponseEntity.status(HttpStatus.FORBIDDEN)
    //                    .body(HttpResponse.builder()
    //                            .statusCode(HttpStatus.FORBIDDEN.value())
    //                            .status(HttpStatus.FORBIDDEN)
    //                            .message("Login failed")
    //                            .developerMessage(e.getMessage())
    //                            .build());
    //        }

        }
        private UserDTO getAuthenticatedUser(Authentication authentication) {
            return ((UserPrincipal) authentication.getPrincipal()).getUser();
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
        @GetMapping("profile")
        public ResponseEntity<HttpResponse> profile(Authentication authentication){
            UserDTO user = userService.getUserByEmail(authentication.getName());
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .data(of("user",user))
                            .message("Profile Retreived")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        @GetMapping("/verify/code/{email}/{code}")
        public ResponseEntity<HttpResponse> verifyCode(@PathVariable String email, @PathVariable String code){
            UserDTO user = userService.verifyCode(email,code);
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .data(of("user",user,"access_token",tokenProvider.createAccessToken(getUserPrincipal(user))
                                    ,"refresh_token",tokenProvider.createRefreshToken(getUserPrincipal(user))))
                            .message("Login successful")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        @GetMapping("/verify/account/{key}")
        public ResponseEntity<HttpResponse> verifyAccount(@PathVariable String key){
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message(userService.verifyAccountKey(key).getEnabled()?"Account already verified":"Account verified")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        @GetMapping("/refresh/token")
        public ResponseEntity<HttpResponse> refreshToken(HttpServletRequest request){
            if(isHeaderAndTokenValid(request)){
                String token = request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length());
                UserDTO user = userService.getUserByEmail(tokenProvider.getSubject(token,request));
                return ResponseEntity.ok().body(
                        HttpResponse.builder()
                                .timeStamp(now().toString())
                                .data(of("user",user,"access_token",tokenProvider.createAccessToken(getUserPrincipal(user))
                                        ,"refresh_token",token))
                                .message("Token Refreshed")
                                .status(HttpStatus.OK)
                                .statusCode(HttpStatus.OK.value())
                                .build());
            }else{
                return ResponseEntity.badRequest().body(
                                HttpResponse.builder()
                                        .timeStamp(now().toString())
                                        .reason("refresh token is invalid or missing")
                                        .developerMessage("refresh token is invalid or missing")
                                        .status(HttpStatus.BAD_REQUEST)
                                        .statusCode(HttpStatus.BAD_REQUEST.value())
                                        .build());
            }

        }

        private boolean isHeaderAndTokenValid(HttpServletRequest request) {
            return request.getHeader(AUTHORIZATION) !=null
                    && request.getHeader(AUTHORIZATION).startsWith(TOKEN_PREFIX)
                    && tokenProvider.isTokenValid(
                            tokenProvider.getSubject(request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length()),request),
                            request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length()));
        }

        @RequestMapping("/error")
        public ResponseEntity<HttpResponse> handleError(HttpServletRequest request){
            return ResponseEntity.badRequest().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("Error Occurred"+request.getMethod())
                            .status(NOT_FOUND)
                            .statusCode(NOT_FOUND.value())
                            .build());
        }

        @GetMapping("/resetpassword/{email}")
        public ResponseEntity<HttpResponse> resetPassword(@PathVariable("email") String email){
            userService.resetPassword(email);
            return ResponseEntity.created(getUri()).body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("Password reset mail has been sent to your email. Please use it to reset your password")
                            .status(HttpStatus.CREATED)
                            .statusCode(HttpStatus.CREATED.value())
                            .build());
        }

        @GetMapping("/verify/password/{key}")
        public ResponseEntity<HttpResponse> verifyPassword(@PathVariable("key") String key){
            UserDTO userDTO = userService.verifyPasswordKey(key);
            return ResponseEntity.created(getUri()).body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .data(of("user", userDTO))
                            .message("Please enter your new password")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        @PostMapping("/resetpassword/{key}/{password}/{confirmPassword}")
        public ResponseEntity<HttpResponse> resetPasswordWithKey(@PathVariable("key") String key,@PathVariable String password,@PathVariable String confirmPassword){
            userService.renewPassword(key,password,confirmPassword);
            return ResponseEntity.created(getUri()).body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .message("password reset successfully")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        private URI getUri(){
            return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/get/<userId>").toUriString());
        }

        private ResponseEntity<HttpResponse> sendResponse(UserDTO user) {
            return ResponseEntity.ok().body(
                    HttpResponse.builder()
                            .timeStamp(now().toString())
                            .data(of("user",user,"access_token",tokenProvider.createAccessToken(getUserPrincipal(user))
                            ,"refresh_token",tokenProvider.createRefreshToken(getUserPrincipal(user))))
                            .message("Loged in successfully")
                            .status(HttpStatus.OK)
                            .statusCode(HttpStatus.OK.value())
                            .build());
        }

        private UserPrincipal getUserPrincipal(UserDTO user) {
            return new UserPrincipal(toUser(userService.getUserByEmail(user.getEmail())),roleService.getRoleByUserId(user.getId()));
        }

        private Authentication authenticate(String email, String password) {
            try {
                Authentication authentication = authenticationManager.authenticate(unauthenticated(email, password));
                return authentication;
            }catch (Exception e) {
                processError(request,response,e);
                throw new ApiException(e.getMessage());
            }
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
