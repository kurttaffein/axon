package axon.ui.user;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDTORepository {

    private Map<String, UserDTO> users = new HashMap<>();

    public UserDTO getUser(String name) {
        UserDTO userDTO = users.get(name);
        if (userDTO != null) {
            return userDTO;
        } else {
            throw new IllegalArgumentException("User not found :(");
        }
    }

    public Collection<UserDTO> getAllUsers() {
        return users.values();
    }

    public void storeUser(UserDTO userDTO) {
        users.put(userDTO.getName(), userDTO);
    }

    public void updateEmail(String name, String email) {
        UserDTO userDTO = users.get(name);
        if (userDTO != null) {
            userDTO.setEmail(email);
        }
    }
}
