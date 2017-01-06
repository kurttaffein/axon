package axon.ui.user;

import axon.core.Application;
import axon.core.event.EmailAddressUpdatedEvent;
import axon.core.event.UserRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserDTOListener {

    @Autowired
    private Application application;

    @Autowired
    private UserDTORepository userDTORepository;

    @PostConstruct
    public void setup() {
        application.registerListener(this);
    }

    @EventHandler
    public void handle(UserRegisteredEvent userRegisteredEvent) {
        userDTORepository.storeUser(new UserDTO(userRegisteredEvent.getUuid(), userRegisteredEvent.getName(), userRegisteredEvent.getEmailAddress()));
    }
}
