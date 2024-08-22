package io.getarrays.securecapita.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class SettingsForm {
    @NotNull(message = "Field can't be null")
    private Boolean enabled;
    @NotNull(message = "Field can't be null")
    private Boolean notLocked;
}
