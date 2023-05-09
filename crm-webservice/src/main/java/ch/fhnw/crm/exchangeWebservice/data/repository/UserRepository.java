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

	//Find user by item id and user id
	@Query("SELECT u FROM User u JOIN u.items i WHERE i.id = :itemId AND u.userId = :userId")
	User findByItemIdAndUserId(Long itemId, Long userId);

	// Find user by user id
	User  findByUserId(Long userId);

	// Delete user by username
	void deleteByUsername(String username);

	// Count number of users
	long count();

    Object findByEmailAndUserIdAndNameNot(String email, Long userId, String username);



	

    

	
	

	
}
