package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.crm.exchangeWebservice.data.domain.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	Agent findByEmail(String email);
	Agent findByEmailAndIdNot(String email, Long agentId);
	Agent findByEmailAndIdAndNameNot(String email, Long agentId, String name);
}
