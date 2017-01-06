package axon.core;

import axon.core.infrastructure.mail.MailClient;
import axon.core.infrastructure.mail.VirtualMailServer;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerAdapter;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Application {
    @Autowired
    private CommandGateway commandGateway;
    private MailClient mailClient;

    public Application() {
        mailClient = new VirtualMailServer();
    }

    public <T> T execute(Object command) {
        return commandGateway.sendAndWait(command);
    }

    /**
     * Set before init is called
     */
    public void setMailClient(MailClient mailClient) {
        this.mailClient = mailClient;
    }

    public VirtualMailServer getVirtualMailServer() {
        return (VirtualMailServer)mailClient;
    }
}