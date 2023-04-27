package ch.fhnw.crm.exchangeWebservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemStatus;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import ch.fhnw.crm.exchangeWebservice.data.repository.ItemCategoryRepository;
import ch.fhnw.crm.exchangeWebservice.data.repository.ItemRepository;
import jakarta.validation.Valid;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ItemService {
    @Autowired
	private ItemRepository itemRepository;

	
	// save new item
	public Item saveItem(@Valid Item item) throws Exception {
		if (item.getItemId() == null) {
			if (itemRepository.findByItemTitleAndUser(item.getItemTitle(), item.getItemId()) != null) {
				throw new Exception("Item title " + item.getItemTitle() +  item.getItemId() + " already assigned another item.");
			}
		} else if (itemRepository.findByUser(item.getUser()) != null) {
			throw new Exception("Item name " + item.getItemTitle() + " already assigned another user.");
		}
		return itemRepository.save(item);
	}

	// get item by id

	public Item getItemById(Long itemId) {
		return itemRepository.findById(itemId).orElse(null);
	}

	// get all items

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	// get item by user

	public List<Item> getItemByUser(User user) {
		return itemRepository.findByUser(user);
	}

	//get all items by item category
	public List <Item> getAllItemsByItemCategory(ItemCategory itemCategory) {
		return itemRepository.findByItemCategory(itemCategory);
	}

	// update an item

	public Item updateItem(@Valid Item item) throws Exception {
		if (item.getItemId() == null) {
			throw new Exception("Item id is null");
		}
		if (itemRepository.findByItemTitleAndUser(item.getItemTitle(), item.getItemId()) != null) {
			throw new Exception("Item title " + item.getItemTitle() + " already assigned another item.");
		}
		return itemRepository.save(item);

	}

		// delete an item

		public Item deleteItem(Long itemId) {
			itemRepository.deleteById(itemId);
			return null;
		}
	

		// get all items belonging to one specific user with item status AVAILABLE

		public List<Item> getItemByUserAndItemStatus(User user, ItemStatus available) {
			return itemRepository.findByItemStatusAndUser(ItemStatus.AVAILABLE, user);
		}

		// get all items belonging to one specific user with item status NOTAVAILABLE

		public List<Item> getItemByUserAndItemStatusNotAvailable(User user) {
			return itemRepository.findByItemStatusAndUser(ItemStatus.NOTAVAILABLE, user);
		}

		// search for items by title

		public List<Item> searchItemByTitle(String searchString) {
			return itemRepository.findByTitleContainingOrderByListingDateAsc(searchString);
		}

		// search for items by title and description using keywords

		public List<Item> searchItems(String keyword) {

			List<Item> items = new ArrayList <> ();
			String[] keywords =keyword.split("\\s+");
			for (String k : keywords) {
				List<Item> itemList = itemRepository.findByTitleContainingOrDescriptionContainingIgnoreCase (k, k);
				items.addAll(itemList);
			}

			return items;
		}



		// count all items on the platform

		public long countAllItems() {
			return itemRepository.count();
		}

		// count all items with a transaction

		public long countAllItemsWithTransaction() {
			return itemRepository.countByTransactionIsNotNull();
		}

		// find all items with no transaction

		public List<Item> findAllItemsWithNoTransaction() {
			return itemRepository.findByTransactionIsNull();
		}

		//find all item listed on a certain date

		public List<Item> findAllItemsListedOnDate(Date date) {
			return itemRepository.findByItemListingDate(date);
		}

		public List<Item> getItemByUser(Long long1) {
			return null;
		}
	
	}

	

	


