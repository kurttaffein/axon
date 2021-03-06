package axon.core;

import axon.core.Application;
import axon.core.User;
import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailCommand;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationTest {
    public static final String NAME = "Name";
    public static final String OLD_EMAIL_ADDRESS = "old email";
    public static final String NEW_EMAIL_ADDRESS = "email";

    private Application application = new Application();

    @Before
    public void setup() {
        application.init();
    }

    //1 USER AGGREGATE
    @Test
    public void iCanRegisterAUsersWithANameAndAnEmailAddress() throws Exception {
        RegisterUserCommand command = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS); //TODO
        UUID uuid = command.getUuid();

        application.execute(command);
        User user = application.getUser(uuid);
        assertThat(user.getName()).isEqualTo(NAME);
        assertThat(user.getEmail()).isEqualTo(OLD_EMAIL_ADDRESS);
    }

    @Test
    public void iCanUpdateTheEmailAddress() throws Exception {
        RegisterUserCommand registerUserCommand = new RegisterUserCommand(NAME, OLD_EMAIL_ADDRESS);
        UpdateEmailCommand updateEmailCommand = new UpdateEmailCommand(registerUserCommand.getUuid(), NEW_EMAIL_ADDRESS);
        UUID uuid = registerUserCommand.getUuid();

        application.execute(registerUserCommand);
        User user = application.getUser(uuid);
        assertThat(user.getEmail()).isEqualTo(OLD_EMAIL_ADDRESS);

        application.execute(updateEmailCommand); //TODO
        user = application.getUser(uuid);
        assertThat(user.getEmail()).isEqualTo(NEW_EMAIL_ADDRESS);
    }
}