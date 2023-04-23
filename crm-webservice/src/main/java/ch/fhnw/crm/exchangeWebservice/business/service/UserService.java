package ch.fhnw.crm.exchangeWebservice.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import ch.fhnw.crm.exchangeWebservice.data.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
@Validated
public class UserService {
    
    @Autowired
    private UserRepository agentRepository;
    @Autowired
    Validator validator;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    // save new user

    public void saveUser(@Valid User user) throws Exception {
        if (user.getUserId() == null) {
            if (agentRepository.findByEmail(user.getEmail()) != null) {
                throw new Exception("Email address " + user.getEmail() + " already assigned another user.");
            }
        } else if (agentRepository.findByEmailAndIdAndNameNot(user.getEmail(), user.getUserId(),user.getUsername()) != null) {
            throw new Exception("Email address " + user.getEmail() + " and name " + user.getUsername() + " already assigned another user.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        agentRepository.save(user);
    }
    
// get current user by user id

public User getCurrentUserById(Long userId) {
    return UserRepository.findByUserId(userId);
}


// edit existing user by user id

public void editUserById(Long userId, User user) throws Exception {
    User existingUser = UserRepository.findByUserId(userId);
    if (existingUser == null) {
        throw new Exception("User with id " + userId + " does not exist.");
    }
    if (UserRepository.findByUsernameAndIdNot(user.getUsername(), userId) != null) {
        throw new Exception("Username " + user.getUsername() + " and user id " + user.getUserId() + " already assigned another user.");
    }
    existingUser.setEmail(user.getEmail());
    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
    agentRepository.save(existingUser);

}

}
