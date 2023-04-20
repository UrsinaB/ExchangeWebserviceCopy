package ch.fhnw.crm.exchangeWebservice.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemStatus;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	//find all items belonging to a specific user
	List<Item> findByUser(User user);
	List<Item> findByUser_Id(Long userId);
	List<Item> findByUsername(String username);

	//find all items belonging to a specific category
	List<Item> findByItemCategory(ItemCategory itemCategory);
	List<Item> findByCategoryName(String categoryName);

	//find all times listed on a certain date
	List<Item> findByListingDate(String listingDate);

	//find all items with a specific status
	List<Item> findByItemStatus(ItemStatus itemStatus);
	List<Item> findByItemStatusAndUser_Id(ItemStatus itemStatus, Long userId);
 
	// Find all items with a title containing the given search string, sorted by listing date in ascending order
	List<Item> findByTitleContainingOrderByListingDateAsc(String searchString);

	//Find all items with no transaction
	List<Item> findByTransactionIsNull();

	//Find all items with a transaction
	List<Item> findByTransactionIsNotNull();

	//find all items
	List<Item> findAll();

	//Count all items
	long count();

	//Delete item by title
	void deleteByTitle(String title);

	//Delete item by title and username
	void deleteByTitleAndUser_Username(String title, String username);

	//Delete items that have a transaction by username
	void deleteByTransactionIsNotNullAndUser_Username(String username);

	//Delete all items of a specific user
	void deleteByUser_Username(String username);

	
}