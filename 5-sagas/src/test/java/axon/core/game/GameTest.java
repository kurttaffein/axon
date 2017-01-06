package axon.core.game;

import axon.core.game.command.RegisterGameCommand;
import axon.core.game.event.GameRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    public static final String STEAM_ID = "steamId";
    public static final String NAME = "name";
    private FixtureConfiguration<Game> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(Game.class);
    }

    @Test
    public void whenIRegisterAGame_thenAGameIsRegistered() {
        RegisterGameCommand registerGameCommand = new RegisterGameCommand(STEAM_ID, NAME);

        GameRegisteredEvent gameRegisteredEvent = new GameRegisteredEvent(registerGameCommand.getGameId(), STEAM_ID, NAME);

        fixture.given()
                .when(registerGameCommand)
                .expectEvents(gameRegisteredEvent);
    }

}
