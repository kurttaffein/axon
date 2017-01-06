package axon.core;

import axon.core.event.EmailAddressUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
import axon.core.infrastructure.mail.MailClient;
import org.axonframework.eventhandling.annotation.EventHandler;

public class MailEventListener {

    private MailClient mailClient;

    public MailEventListener(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    @EventHandler
    public void userRegistered(UserRegisteredEvent userRegisteredEvent) {
        mailClient.mail(userRegisteredEvent.getEmailAddress(), "Welcome!");
    }

    @EventHandler
    public void emailAddressUpdated(EmailAddressUpdatedEvent emailAddressUpdatedEvent) {
        mailClient.mail(emailAddressUpdatedEvent.getNewEmailAddress(), "Email address successfully changed!");
    }
}
