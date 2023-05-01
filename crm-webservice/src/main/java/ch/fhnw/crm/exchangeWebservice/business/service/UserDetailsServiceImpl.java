package ch.fhnw.crm.exchangeWebservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ch.fhnw.crm.exchangeWebservice.data.repository.UserRepository;



@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
       ch.fhnw.crm.exchangeWebservice.data.domain.User user = userRepository.findByEmail(username);
        
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        UserDetails userDetails = User.builder()
                                .username(username)
                                .password(user.getPassword())
                                .authorities("READ","ROLE_" + ((ch.fhnw.crm.exchangeWebservice.data.domain.User) user).getRole())
                                .build();

        return userDetails;
    }

    
    
}












