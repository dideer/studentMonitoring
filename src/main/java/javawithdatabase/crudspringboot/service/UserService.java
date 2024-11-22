package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    Optional<User> findByUsername(String username);

    User findById(Long userId);

    // Check if the old password matches
    boolean checkOldPassword(User user, String oldPassword);

    // Change the user's password
    void changePassword(User user, String newPassword);


}
