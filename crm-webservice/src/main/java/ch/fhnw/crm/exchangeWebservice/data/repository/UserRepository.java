package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;

// declaring a database relation user (the user of the application)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Find user by username
	User findByUsername(String username);
	User findByUsernameAndPassword(String username, String password);
	User findByEmail(String email);
	User findByItemId(Long itemId);
	User findByTransactionId(Long transactionId);
	User findByProvidingTransactionsId(Long transactionId);
	User findByReceivingTransactionsId(Long transactionId);
	User findByProvidingTransactionsIdAndUsername(Long transactionId, String username);
	User findByEmailAndIdAndNameNot(String email, Long userId, String username);
	User  findByUserId(Long userId);

	// Delete user by username

	void deleteByUsername(String username);

	// Count number of users
	long count();


    

	
	

	
}
