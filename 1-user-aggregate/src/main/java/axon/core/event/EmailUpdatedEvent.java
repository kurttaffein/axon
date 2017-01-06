package axon.core.event;

public class EmailUpdatedEvent {

    private String email;

    public EmailUpdatedEvent(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
