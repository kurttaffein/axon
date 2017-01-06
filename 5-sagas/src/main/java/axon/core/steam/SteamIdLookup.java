package axon.core.steam;

import axon.core.game.event.GameRegisteredEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class SteamIdLookup {

    private Map<UUID, String> accounts = new HashMap<>();
    private Map<UUID, String> games = new HashMap<>();

    public Optional<String> getSteamAccountIdForUser(UUID userId) {
        return Optional.ofNullable(accounts.get(userId));
    }

    public String getSteamGameIdForGame(UUID gameId) {
        return games.get(gameId);
    }

    @EventHandler
    public void steamAccountLinked(SteamAccountLinkedEvent steamAccountLinkedEvent) {
        accounts.put(steamAccountLinkedEvent.getUserId(), steamAccountLinkedEvent.getSteamUserId());
    }

    @EventHandler
    public void gameRegisteredEvent(GameRegisteredEvent gameRegisteredEvent) {
        games.put(gameRegisteredEvent.getGameId(), gameRegisteredEvent.getSteamId());
    }
}
