package axon.ui.user;

import java.util.UUID;

public class UserDTO {

    private UUID uuid;
    private String name;
    private String email;

    public UserDTO(UUID uuid, String name, String email) {
        this.uuid = uuid;
        this.name = name;
        this.email = email;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}