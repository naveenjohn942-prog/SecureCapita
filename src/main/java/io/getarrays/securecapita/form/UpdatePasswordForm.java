package io.getarrays.securecapita.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordForm {
    @NotEmpty(message = "Current password cannot be empty")
    private String currentPassword;
    @NotEmpty(message = "New password cannot be empty")
    private String newPassword;
    @NotEmpty(message="Password cannot be empty")
    private String confirmNewPassword;
}
