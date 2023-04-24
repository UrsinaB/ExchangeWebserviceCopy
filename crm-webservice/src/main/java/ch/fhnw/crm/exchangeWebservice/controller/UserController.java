package ch.fhnw.crm.exchangeWebservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.crm.exchangeWebservice.business.service.UserService;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import io.swagger.v3.oas.annotations.Hidden;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @PostMapping("/register")
    public ResponseEntity<Void> postRegister(@RequestBody User user) {
        try {
            UserService.saveUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    // get current user by user id
    @GetMapping("/profile/{userId}")
    public @ResponseBody User getProfile(@PathVariable(value = "userId") String userId) {
        return UserService.getCurrentUserById(Long.parseLong(userId));
    }

   // edit current user by user id
    @PutMapping("/profile/{userId}")
    public ResponseEntity<Void> putProfile(@RequestBody User user, @PathVariable(value = "userId") String userId) {
        try {
            user.setUserId(Long.parseLong(userId));
            UserService.saveUser(user);
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
