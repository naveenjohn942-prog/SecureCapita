package io.getarrays.securecapita.enumeration;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum EventType {
    LOGIN_ATTEMPT("You tried to log in"),
    LOGIN_ATTEMPT_FAILURE("You tried to log in and failed"),
    LOGIN_ATTEMPT_SUCCESS("You tried to log in and succeeded"),
    PROFILE_UPDATE("You updated your profile information"),
    PROFILE_PICTURE_UPDATE("You updated your profile picture"),
    ROLE_UPDATE("You updated your role and permission"),
    ACCOUNT_SETTINGS_UPDATE("You updated your account settings"),
    PASSWORD_UPDATE("You updated your password"),
    MFA_UPDATE("You updated your profile MFA settings");

    private final String description;
    EventType(String description1) {
        this.description = description1;
    }
}
