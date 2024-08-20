package io.getarrays.securecapita.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateForm {
    @NotNull(message = "ID cannot be empty")
    private Long id;
    @NotEmpty(message = "Name cannot be empty")
    private String firstName;
    @NotEmpty(message = "Name cannot be empty")
    private String lastName;
    private String phone;
    private String address;
    @NotEmpty(message="email cannot be empty")
    @Email(message = "Invalid email entered. Please enter valid email id")
    private String email;
    private Boolean enabled;
    private String title;
    private String bio;
}
