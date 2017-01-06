package axon.core;

import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailCommand;
import axon.core.event.EmailUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserTest {
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final UUID USER_UUID = UUID.randomUUID();

    private FixtureConfiguration<User> fixture;

    @Before
    public void setup() {
        fixture = Fixtures.newGivenWhenThenFixture(User.class);
    }

    //1 USER AGGREGATE
    @Test
    public void whenIRegisterAUser_thenAUserIsRegistered() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, EMAIL);

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(registerUserCommand.getUuid(), registerUserCommand.getName(), registerUserCommand.getEmail());

        fixture.given()
                .when(registerUserCommand)
                .expectEvents(userRegisteredEvent);
    }

    @Test
    public void whenIRegisterAUserWithoutName_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand("", EMAIL);

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void whenIRegisterAUserWithoutAnEmailAddress_thenAnExceptionIsThrown() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, "");

        fixture.given()
                .when(registerUserCommand)
                .expectException(IllegalArgumentException.class);
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddress_thenTheEmailAddressIsUpdated() throws Exception {
        UpdateEmailCommand updateEmailCommand = new UpdateEmailCommand(USER_UUID, EMAIL);

        EmailUpdatedEvent emailUpdatedEvent = new EmailUpdatedEvent(EMAIL);

        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, EMAIL))
                .when(updateEmailCommand)
                .expectEvents(emailUpdatedEvent);
    }

    @Test
    public void givenAUser_whenIUpdateTheEmailAddressToAnEmptyOne_thenAnExceptionIsThrown() throws Exception {
        UpdateEmailCommand updateEmailCommand = new UpdateEmailCommand(USER_UUID, "");

        EmailUpdatedEvent emailUpdatedEvent = new EmailUpdatedEvent(EMAIL);

        fixture.given(new UserRegisteredEvent(USER_UUID, NAME, EMAIL))
                .when(updateEmailCommand)
                .expectException(IllegalArgumentException.class);
    }
}
