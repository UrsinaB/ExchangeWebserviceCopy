package ch.fhnw.crm.exchangeWebservice.business.service;


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
    private UserRepository userRepository;
    @Autowired
    Validator validator;
    
    

    public void saveUser(@Valid User user) throws Exception {
        if (user.getUserId() == null) {
            if (userRepository.findByEmail(user.getEmail()) != null) {
                throw new Exception("Email address " + user.getEmail() + " already assigned another user.");
            }
        } else if (userRepository.findByEmailAndUserIdAndNameNot(user.getEmail(), user.getUserId(), user.getUsername()) != null) {
            throw new Exception("Email address " + user.getEmail() + " and name " + user.getUsername() + " already assigned another user.");
        }
        userRepository.save(user);
    }

    //count number of users
    public long count() {
        return userRepository.count();
    }

    //get current user
    public User getCurrentUser() {
        return userRepository.findByUserId(1L);
    }

    public User getCurrentUserById(long parseLong) {
        return null;
    }

    // find user by item id
    public User findByItemId(Long itemId) {
        return userRepository.findByItemId(itemId);
    }











}
