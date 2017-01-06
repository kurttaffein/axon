package axon.core.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class UpdateEmailCommand {

    @TargetAggregateIdentifier
    private UUID uuid;
    private String email;

    public UpdateEmailCommand(UUID uuid, String email) {
        this.uuid = uuid;
        this.email = email;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }
}
