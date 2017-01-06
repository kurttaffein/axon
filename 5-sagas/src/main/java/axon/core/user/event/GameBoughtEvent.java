package axon.core.user.event;

import java.util.UUID;

public class GameBoughtEvent {

    private UUID purchaseId = UUID.randomUUID();
    private UUID userId;
    private UUID gameId;

    public GameBoughtEvent(UUID userId, UUID gameId) {
        this.userId = userId;
        this.gameId = gameId;
    }

    public UUID getPurchaseId() {
        return purchaseId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getGameId() {
        return gameId;
    }

}
