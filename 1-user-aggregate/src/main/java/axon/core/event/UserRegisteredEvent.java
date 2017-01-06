package axon.core.event;

import java.util.UUID;

public class UserRegisteredEvent {
    private UUID uuid;
    private String name;
    private String email;

    public UserRegisteredEvent(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
