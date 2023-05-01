package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;

// declaring a database relation user (the user of the application)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	// Find user by username
	User findByUsername(String username);

	// Find user by username and password
	User findByUsernameAndPassword(String username, String password);

	// Find user by email
	User findByEmail(String email);

	// Find user by item id
	@Query("SELECT u FROM User u JOIN u.items i WHERE i.id = :itemId")
	User findByItemId(Long itemId);

	//Find user by transation id
	@Query("SELECT u FROM User u JOIN u.providingTransactions t WHERE t.id = :transactionId")
	User findByTransactionId(Long transactionId);

	// FInd the id of the user how was the providing user in the transaction
	@Query("SELECT u FROM User u JOIN u.providingTransactions t WHERE t.id = :transactionId")
	User findByProvidingTransactionsId(Long transactionId);

	// Find the id of the user how was the receiving user in the transaction
	@Query("SELECT u FROM User u JOIN u.receivingTransactions t WHERE t.id = :transactionId")
	User findByReceivingTransactionsId(Long transactionId);

	// Find user by user id
	User  findByUserId(Long userId);

	// Delete user by username
	void deleteByUsername(String username);

	// Count number of users
	long count();


	@Query("SELECT u FROM User u WHERE u.email = :email AND u.userId != :userId AND u.username <> :username")
	User findByEmailAndUserIdAndNameNot(String email, Long userId, String username);

	

    

	
	

	
}
