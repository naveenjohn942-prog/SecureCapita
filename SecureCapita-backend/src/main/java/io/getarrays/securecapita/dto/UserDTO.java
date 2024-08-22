package io.getarrays.securecapita.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
