package ch.fhnw.crm.crmwebservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.crm.crmwebservice.data.domain.Customer;
import ch.fhnw.crm.crmwebservice.data.repository.CustomerRepository;

import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AgentService agentService;

	public Customer editCustomer(@Valid Customer customer, Long agentId) throws Exception {
		if (customer.getId() == null) {
			if (customerRepository.findByMobile(customer.getMobile()) == null) {
				customer.setAgent(agentService.getCurrentAgent(agentId));
				return customerRepository.save(customer);
			}
			throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
		}
		if (customerRepository.findByMobileAndIdNot(customer.getMobile(), customer.getId()) == null) {
			if (customer.getAgent() == null) {
				customer.setAgent(agentService.getCurrentAgent(agentId));
			}
			return customerRepository.save(customer);
		}
		throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
	}

	public void deleteCustomer(Long customerId)
	{
		customerRepository.deleteById(customerId);
	}
	
	public Customer findCustomerById(Long customerId, Long agentId) throws Exception {
		List<Customer> customerList = customerRepository.findByIdAndAgentId(customerId, agentId);
		if(customerList.isEmpty()){
			throw new Exception("No customer with ID "+customerId+" found.");
		}
		return customerList.get(0);
	}

	public List<Customer> findAllCustomers(Long agentId) {
		return customerRepository.findByAgentId(agentId);
	}

	public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
