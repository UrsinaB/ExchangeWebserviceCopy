package ch.fhnw.crm.crmwebservice.controller;

import ch.fhnw.crm.crmwebservice.business.service.AgentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.crm.crmwebservice.data.domain.Agent;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;


    @PostMapping("/register")
    public ResponseEntity<Void> postRegister(@RequestBody Agent agent) {
        try {
            agentService.saveAgent(agent);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allagents")
    public List<Agent> all() {
       return agentService.getAllAgents();
    }

    @GetMapping("/profile")
    public @ResponseBody Agent getProfile() {
        return agentService.getCurrentAgent();
    } 

    @PutMapping("/profile")
    public ResponseEntity<Void> putProfile(@RequestBody Agent agent) {
        try {
            agent.setId(agentService.getCurrentAgent().getId());
            agentService.saveAgent(agent);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/validate", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ResponseEntity<Void> init() {
        return ResponseEntity.ok().build();
    }
    
}
