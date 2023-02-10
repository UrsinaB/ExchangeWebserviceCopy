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
import io.swagger.v3.oas.annotations.Hidden;

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

    @GetMapping("/profile/{agentId}")
    public @ResponseBody Agent getProfile(@PathVariable(value = "agentId") String agentId) {
        return agentService.getCurrentAgent(Long.parseLong(agentId));
    } 

    @PutMapping("/profile/{agentId}")
    public ResponseEntity<Void> putProfile(@RequestBody Agent agent, @PathVariable(value = "agentId") String agentId) {
        try {
            agent.setId(Long.parseLong(agentId));
            agentService.saveAgent(agent);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @Hidden
    @RequestMapping(value = "/validate", method = {RequestMethod.GET, RequestMethod.HEAD})
    public ResponseEntity<Void> init() {
        return ResponseEntity.ok().build();
    }
    
}
