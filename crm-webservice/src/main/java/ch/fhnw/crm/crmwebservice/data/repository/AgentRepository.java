package ch.fhnw.crm.crmwebservice.data.repository;

import ch.fhnw.crm.crmwebservice.data.domain.Agent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	Agent findByEmail(String email);
	Agent findByEmailAndIdNot(String email, Long agentId);
	Agent findByEmailAndIdAndNameNot(String email, Long agentId, String name);
}
