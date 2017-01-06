package axon.core.steam;

import axon.core.infrastructure.steam.SteamGateway;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SteamRegistrationSaga extends AbstractAnnotatedSaga {
    @Autowired
    private transient SteamGateway steamGateway;
    @Autowired
    private transient SteamIdLookup steamIdLookup;
    private UUID gameId;

    public void setSteamGateway(SteamGateway steamGateway) {
        this.steamGateway = steamGateway;
    }

    public void setSteamIdLookup(SteamIdLookup steamIdLookup) {
        this.steamIdLookup = steamIdLookup;
    }

    @SagaEventHandler(associationProperty = "purchaseId")
    @StartSaga
    public void handle(GameBoughtEvent gameBoughtEvent) {
        Optional<String> steamAccountIdForUser = steamIdLookup.getSteamAccountIdForUser(gameBoughtEvent.getUserId());

        if (steamAccountIdForUser.isPresent()) {
            // User has already linked his steam account
            notifySteam(gameBoughtEvent.getGameId(), steamAccountIdForUser.get());
            end();
        } else {
            gameId = gameBoughtEvent.getGameId();
            associateWith("userId", gameBoughtEvent.getUserId().toString());
        }
    }

    private void notifySteam(UUID gameId, String steamAccountIdForUser) {
        String steamGameIdForGame = steamIdLookup.getSteamGameIdForGame(gameId);
        steamGateway.registerGame(steamAccountIdForUser, steamGameIdForGame);
    }

    @SagaEventHandler(associationProperty = "userId")
    @EndSaga
    public void handle(SteamAccountLinkedEvent steamAccountLinkedEvent) {
        notifySteam(gameId, steamAccountLinkedEvent.getSteamUserId());
    }

}
