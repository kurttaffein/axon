package axon.core.game.command;

import java.util.UUID;

public class RegisterGameCommand {

    private UUID gameId = UUID.randomUUID();
    private String steamId;
    private String name;

    public RegisterGameCommand(String steamId, String name) {
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
