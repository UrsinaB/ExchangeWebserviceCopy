package ch.fhnw.crm.crmwebservice.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
/* import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; */
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.crm.crmwebservice.data.domain.Agent;
import ch.fhnw.crm.crmwebservice.data.repository.AgentRepository;

import jakarta.validation.Valid;
import jakarta.validation.Validator;

@Service
@Validated
public class AgentService {
    
    @Autowired
    private AgentRepository agentRepository;
    @Autowired
    Validator validator;
    

  /*   @Bean
    public static PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    
        } */

    public void saveAgent(@Valid Agent agent) throws Exception {
        if (agent.getId() == null) {
            if (agentRepository.findByEmail(agent.getEmail()) != null) {
                throw new Exception("Email address " + agent.getEmail() + " already assigned another agent.");
            }
        } else if (agentRepository.findByEmailAndIdNot(agent.getEmail(), agent.getId()) != null) {
            throw new Exception("Email address " + agent.getEmail() + " already assigned another agent.");
        }
        //agent.setPassword(passwordEncoder().encode(agent.getPassword()));
        agent.setPassword(agent.getPassword());
        agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getCurrentAgent() {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return agentRepository.findByEmail("agent@mycrm.com");
    } 
}
