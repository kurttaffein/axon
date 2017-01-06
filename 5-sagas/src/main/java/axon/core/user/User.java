package axon.core.user;

import axon.core.user.command.BuyGameCommand;
import axon.core.user.command.LinkSteamAccountCommand;
import axon.core.user.command.RegisterUserCommand;
import axon.core.user.command.UpdateEmailAddressCommand;
import axon.core.user.event.EmailAddressUpdatedEvent;
import axon.core.user.event.GameBoughtEvent;
import axon.core.user.event.SteamAccountLinkedEvent;
import axon.core.user.event.UserRegisteredEvent;
import axon.core.user.exception.GameAlreadyBoughtException;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.common.Assert;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User extends AbstractAnnotatedAggregateRoot<UUID> {
    @AggregateIdentifier
    private UUID userId;
    private List<UUID> games = new ArrayList<>();

    public User() {}

    @CommandHandler
    public User(RegisterUserCommand registerUserCommand) {
        UUID userId = registerUserCommand.getUserId();
        String name = registerUserCommand.getName();
        String emailAddress = registerUserCommand.getEmailAddress();

        Assert.notNull(userId, "UserId can not be null");
        Assert.notEmpty(name, "Name can not be empty");
        Assert.notEmpty(emailAddress, "Email address can not be empty");

        UserRegisteredEvent userRegisteredEvent = new UserRegisteredEvent(userId, name, emailAddress);
        apply(userRegisteredEvent);
    }


    @EventSourcingHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        this.userId = userRegisteredEvent.getUserId();
    }

    @CommandHandler
    public void updateEmailAddress(UpdateEmailAddressCommand updateEmailAddressCommand) {
        UUID userId = updateEmailAddressCommand.getUserId();
        String newEmailAddress = updateEmailAddressCommand.getNewEmailAddress();

        Assert.notEmpty(newEmailAddress, "The new email address can not be empty");

        EmailAddressUpdatedEvent emailAddressUpdatedEvent = new EmailAddressUpdatedEvent(userId, newEmailAddress);
        apply(emailAddressUpdatedEvent);
    }

    @EventSourcingHandler
    public void emailAddressUpdated(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
    }

    @CommandHandler
    public void buyGame(BuyGameCommand buyGameCommand) {
        UUID userId = buyGameCommand.getUserId();
        UUID gameId = buyGameCommand.getGameId();

        Assert.notNull(userId, "UserId can not be null");
        Assert.notNull(gameId, "GameId can not be null");

        if (games.contains(gameId)) {
            throw new GameAlreadyBoughtException();
        } else {
            GameBoughtEvent gameBoughtEvent = new GameBoughtEvent(userId, gameId);
            apply(gameBoughtEvent);
        }
    }

    @EventSourcingHandler
    public void gameBought(GameBoughtEvent gameBoughtEvent) {
        games.add(gameBoughtEvent.getGameId());
    }

    @CommandHandler
    public void linkSteamAccount(LinkSteamAccountCommand linkSteamAccountCommand) {
        UUID userId = linkSteamAccountCommand.getUserId();
        String steamUserId = linkSteamAccountCommand.getSteamUserId();

        Assert.notNull(userId, "UserId can not be null");
        Assert.notEmpty(steamUserId, "SteamUserId can not be null");

        SteamAccountLinkedEvent steamAccountLinkedEvent = new SteamAccountLinkedEvent(userId, steamUserId);
        apply(steamAccountLinkedEvent);
    }
}
