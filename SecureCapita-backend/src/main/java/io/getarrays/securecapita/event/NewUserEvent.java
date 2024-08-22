package io.getarrays.securecapita.event;

import io.getarrays.securecapita.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class NewUserEvent extends ApplicationEvent {
    private EventType eventType;
    private String email;

    public NewUserEvent(EventType type, String email) {
        super(email);
        this.eventType = type;
        this.email = email;
    }

}
