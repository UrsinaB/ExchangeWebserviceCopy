package ch.fhnw.crm.crmwebservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ch.fhnw.crm.crmwebservice.data.domain.Agent;
import ch.fhnw.crm.crmwebservice.data.repository.AgentRepository;



@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Agent agent = agentRepository.findByEmail(username);
        
        if (agent == null) {
            throw new UsernameNotFoundException(username);
        }
        
        UserDetails user = User.builder()
                                .username(username)
                                .password(agent.getPassword())
                                .authorities("READ","ROLE_" + agent.getRole())
                                .build();

        return user;
    }

    
    
}
