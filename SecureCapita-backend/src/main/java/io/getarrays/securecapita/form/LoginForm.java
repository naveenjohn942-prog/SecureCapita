package io.getarrays.securecapita.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginForm {
    @NotEmpty(message="email cannot be empty")
    @Email(message = "Invalid email entered. Please enter valid email id")
    private String email;
    @NotEmpty(message="Password cannot be empty")
    private String password;
}
