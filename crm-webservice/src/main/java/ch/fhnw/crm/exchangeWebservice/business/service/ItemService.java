package ch.fhnw.crm.exchangeWebservice.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import ch.fhnw.crm.exchangeWebservice.data.domain.User;
import ch.fhnw.crm.exchangeWebservice.data.repository.ItemRepository;
import jakarta.validation.Valid;
import java.util.List;

@Service
@Validated
public class ItemService {
    @Autowired
	private ItemRepository itemRepository;
	@Autowired
	private UserService userService;

	// save new item


	public void saveItem(@Valid Item item) throws Exception {
		if (item.getItemId() == null) {
			if (itemRepository.findByTitleAndUser_Id (item.getTitle(), item.getItemId()) != null) {
				throw new Exception("Item title " + item.getTitle() +  item.getItemId() + " already assigned another item.");
			}
		} else if (itemRepository.findByUser(item.getUser()) != null) {
			throw new Exception("Item name " + item.getTitle() + " already assigned another user.");
		}
		itemRepository.save(item);
	}

	// get all items

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	

	
	}

	

	


