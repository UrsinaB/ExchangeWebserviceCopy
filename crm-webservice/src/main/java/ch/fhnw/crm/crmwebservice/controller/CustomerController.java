package ch.fhnw.crm.crmwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.crm.crmwebservice.business.service.CustomerService;
import ch.fhnw.crm.crmwebservice.data.domain.Customer;

import jakarta.validation.ConstraintViolationException;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/customer/{agentId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer, @PathVariable(value = "agentId") String agentId) {
        try {
            customer = customerService.editCustomer(customer, Long.parseLong(agentId));
         } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }


        return ResponseEntity.ok(customer);
    }

    //TODO::secure with some authority or role
    @GetMapping(path = "/customer", produces = "application/json")
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/customer/{agentId}", produces = "application/json")
    public List<Customer> getCustomers(@PathVariable(value = "agentId") String agentId) {
        return customerService.findAllCustomers(Long.parseLong(agentId));
    }

    @GetMapping(path = "/customer/{customerId}/{agentId}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId, @PathVariable(value = "agentId") String agentId) {
        Customer customer = null;
        try {
            customer = customerService.findCustomerById(Long.parseLong(customerId), Long.parseLong(agentId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(customer);
    }

    @PutMapping(path = "/customer/{customerId}/{agentId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> putCustomer(@RequestBody Customer customer, @PathVariable(value = "customerId") String customerId, @PathVariable(value = "agentId") String agentId) {
        try {
            customer.setId(Long.parseLong(customerId));
            customer = customerService.editCustomer(customer, Long.parseLong(agentId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(customer);
    }

    @DeleteMapping(path = "/customer/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "customerId") String customerId) {
        try {
            customerService.deleteCustomer(Long.parseLong(customerId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().build();
    }
    
}
