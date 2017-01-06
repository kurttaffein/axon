package axon.core.command;

import java.util.UUID;

public class RegisterUserCommand {
    private UUID uuid = UUID.randomUUID();

    private String name;
    private String email;

    public RegisterUserCommand(String name, String email) {
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
