package axon.core.user.event;

import java.util.UUID;

public class SteamAccountLinkedEvent {

    private UUID userId;
    private String steamUserId;

    public SteamAccountLinkedEvent(UUID userId, String steamUserId) {
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
