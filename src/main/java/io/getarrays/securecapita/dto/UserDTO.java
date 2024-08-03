package io.getarrays.securecapita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private Boolean enabled;
    private Boolean nonLocked;
    private Boolean mfaEnabled;
    private LocalDateTime createdOn;
    private String imageUrl;
    private String title;
    private String bio;
    private String roleName;
    private String permissions;
}
