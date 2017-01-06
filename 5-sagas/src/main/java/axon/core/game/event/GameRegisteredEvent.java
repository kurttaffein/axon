package axon.core.game.event;

import java.util.UUID;

public class GameRegisteredEvent {

    private UUID gameId;
    private String steamId;
    private String name;

    public GameRegisteredEvent(UUID gameId, String steamId, String name) {
        this.gameId = gameId;
        this.steamId = steamId;
        this.name = name;
    }

    public UUID getGameId() {
        return gameId;
    }

    public String getSteamId() {
        return steamId;
    }

    public String getName() {
        return name;
    }
}
