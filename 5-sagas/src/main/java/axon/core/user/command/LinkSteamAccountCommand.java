package axon.core.user.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.UUID;

public class LinkSteamAccountCommand {

    @TargetAggregateIdentifier
    private UUID userId;
    private String steamUserId;

    public LinkSteamAccountCommand(UUID userId, String steamUserId) {
        this.userId = userId;
        this.steamUserId = steamUserId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getSteamUserId() {
        return steamUserId;
    }

}
