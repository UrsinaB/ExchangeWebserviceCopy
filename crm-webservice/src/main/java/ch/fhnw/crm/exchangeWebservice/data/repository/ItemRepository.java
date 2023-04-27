package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

	//find all items belonging to a specific user
	List<Item> findByUser(User user);

	//find all item listed on a certain date
	List<Item> findByItemListingDate(Date itemListingDate);

	//find all items with a specific status
	List<Item> findByItemStatus(ItemStatus itemStatus);

	//Find all items with a specific status and belonging to a specific user
	List<Item> findByItemStatusAndUser(ItemStatus itemStatus, User user);
 
	// Find all items with a title containing the given search string, sorted by listing date in ascending order
	List<Item> findByTitleContainingOrderByListingDateAsc(String searchString);

	//Find all items with no transaction
	List<Item> findByTransactionIsNull();

	//Find all items with a transaction
	List<Item> findByTransactionIsNotNull();

	// Count all items with a transaction
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

	List<Item> findByTitleContainingOrDescriptionContainingIgnoreCase(String k, String k2);

	List<Item> findByItemCategory(ItemCategory itemCategory);

	
}
