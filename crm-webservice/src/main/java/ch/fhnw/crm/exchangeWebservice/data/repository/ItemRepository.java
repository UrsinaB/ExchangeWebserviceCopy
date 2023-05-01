package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemStatus;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;

import java.sql.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	//find a specific item by title and acutal User
	Item findByItemTitleAndUser(String itemTitle, Long long1);

	//find a specific item belonging to a specific user
	Item findByItemIdAndUser(Long itemId, User user);

	//find all items belonging to a specific user id
	@Query("SELECT i FROM Item i JOIN i.user u WHERE u.id = :userId")
	List<Item> findByUserId(Long userId);
	
	
	List<Item> findByUser(User user);

	//find all item listed on a certain date
	List<Item> findByItemListingDate(Date itemListingDate);

	//find all items with a specific status
	@Query("SELECT i FROM Item i JOIN i.itemStatus s WHERE s = :itemStatus")
	List<Item> findByItemStatus(ItemStatus itemStatus);

	//Find all items with a specific status and belonging to a specific user
	@Query("SELECT i FROM Item i JOIN i.itemStatus s JOIN i.user u WHERE s = :itemStatus AND u = :user")
	List<Item> findByItemStatusAndUser(ItemStatus itemStatus, User user);
 
	// Find all items with a title containing the given search string, sorted by listing date in ascending order
	List<Item> findByItemTitleContainingIgnoreCaseOrderByItemListingDateAsc(String itemTitle);
	

	//Find all items with no transaction
	@Query("SELECT i FROM Item i WHERE i.transaction IS NULL")
	List<Item> findByTransactionIsNull();

	//Find all items with a transaction
	@Query("SELECT i FROM Item i WHERE i.transaction IS NOT NULL")
	List<Item> findByTransactionIsNotNull();

	// Count all items with a transaction
	@Query("SELECT COUNT(i) FROM Item i WHERE i.transaction IS NOT NULL")
	long countByTransactionIsNotNull();

	//find all items
	List<Item> findAll();

	//Count all items
	long count();

	//Delete item by title
	void deleteByItemTitle(String itemTitle);

	//Delete items that have a transaction and USer
	void deleteByTransactionIsNotNullAndUser(User user);

	//Delete all items of a specific user
	void deleteByUser_Username(String username);


	//search for items by title and description using keywords
	List<Item> findByItemTitleContainingOrItemDescriptionContainingIgnoreCase(String itemTitle, String itemDescription);

	List<Item> findByItemCategory(ItemCategory itemCategory);

	
}
