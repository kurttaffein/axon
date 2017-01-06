package axon.core.game;

import axon.core.game.command.RegisterGameCommand;
import axon.core.game.event.GameRegisteredEvent;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.UUID;

public class Game extends AbstractAnnotatedAggregateRoot<UUID> {

    @AggregateIdentifier
    private UUID gameId;
    private String steamId;
    private String name;

    public Game() {}

    @CommandHandler
    public Game(RegisterGameCommand registerGameCommand) {
        this.gameId = registerGameCommand.getGameId();
        this.steamId = registerGameCommand.getSteamId();
        this.name = registerGameCommand.getName();

        Assert.notNull(gameId, "Game ID cannot be null");
        Assert.notNull(steamId, "Steam ID cannot be empty");
        Assert.notNull(name, "Name cannot be empty");

        GameRegisteredEvent gameRegisteredEvent = new GameRegisteredEvent(gameId, steamId, name);
        apply(gameRegisteredEvent);
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
