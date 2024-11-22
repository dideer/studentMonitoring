package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.repository.UserRepository;
import javawithdatabase.crudspringboot.service.UserService;
import javawithdatabase.crudspringboot.service.sec.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {

        try {
            if (!"teacher".equalsIgnoreCase(user.getRole())) {
                // If the role is not "teacher", set the teacher field to null
                user.setTeacher(null);
            }else if (!"hod".equalsIgnoreCase(user.getRole())) {
                user.setHod(null);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserRegistrationException("Error while registering user" + e.getMessage());
        }
    }


    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    @Override
    public boolean checkOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public void changePassword(User user, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


}
