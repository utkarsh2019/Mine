package tech.mineapp.service;

import org.apache.catalina.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import tech.mineapp.model.service.UserDTO;

/**
 * Interface for defining the functionalities
 * of a service which deals with users
 */
public interface UsersService extends UserDetailsService {
    UserDTO createUser(UserDTO newUserDTO);
    UserDTO getUser(String userId);
    void removeUser(String userId);
    UserDTO updateUser(UserDTO updatedUserDTO);
}
