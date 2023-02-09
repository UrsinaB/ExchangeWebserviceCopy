package ch.fhnw.crm.crmwebservice.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; 
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
    
    @Autowired
    PasswordEncoder passwordEncoder;
    

    public void saveAgent(@Valid Agent agent) throws Exception {
        if (agent.getId() == null) {
            if (agentRepository.findByEmail(agent.getEmail()) != null) {
                throw new Exception("Email address " + agent.getEmail() + " already assigned another agent.");
            }
        } else if (agentRepository.findByEmailAndIdAndNameNot(agent.getEmail(), agent.getId(),agent.getName()) != null) {
            throw new Exception("Email address " + agent.getEmail() + " and name " + agent.getName() + " already assigned another agent.");
        }
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        agentRepository.save(agent);
    }

    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    public Agent getCurrentAgent(Long agentId) {
        Agent agent = agentRepository.findById(agentId).get();
        return agent;
    } 
}
