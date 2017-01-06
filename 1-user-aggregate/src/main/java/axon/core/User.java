package axon.core;

import axon.core.command.RegisterUserCommand;
import axon.core.command.UpdateEmailCommand;
import axon.core.event.EmailUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID uuid;
    private String name;
    private String email;

    public User() {}

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        if (StringUtils.isBlank(registerUserCommand.getName())) {
            throw new IllegalArgumentException("A user must have a name");
        }

        if (StringUtils.isBlank(registerUserCommand.getEmail())) {
            throw new IllegalArgumentException("A user must have an email");
        }

        UUID uuid = registerUserCommand.getUuid();

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(uuid, registerUserCommand.getName(), registerUserCommand.getEmail());
        apply(userRegisteredEvent);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.uuid = userRegisteredEvent.getUuid();
        this.name = userRegisteredEvent.getName();
        this.email = userRegisteredEvent.getEmail();
    }

    @CommandHandler
    public void handleEmailUpdate(UpdateEmailCommand updateEmailCommand) {
        if (StringUtils.isBlank(updateEmailCommand.getEmail())) {
            throw new IllegalArgumentException("New email cannot be blank");
        }

        EmailUpdatedEvent emailUpdatedEvent = new EmailUpdatedEvent(updateEmailCommand.getEmail());
        apply(emailUpdatedEvent);
    }

    @EventSourcingHandler
    public void emailUpdated(EmailUpdatedEvent emailUpdatedEvent) {
        this.email = emailUpdatedEvent.getEmail();
    }
}
