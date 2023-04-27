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
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated


public class ItemCategoryService {

    @Autowired
	private ItemCategoryRepository ItemCategoryRepository;
    @Autowired
    Validator validator;
    

    // save new item category
    public void saveItemCategory(@Valid ItemCategory itemCategory) throws Exception {
        if (ItemCategoryRepository.findByCategoryName(itemCategory.getCategoryName()) != null) { {
                throw new Exception("Item category name " + itemCategory.getCategoryName() + " already assigned another item category.");
            }
        }
        ItemCategoryRepository.save(itemCategory);
    }

    // get all item categories
    public List<ItemCategory> getAllItemCategories() {
        return ItemCategoryRepository.findAll();
    }

    // get item category by id
    public ItemCategory getItemCategoryById(Long ItemCategoryid) {
        ItemCategory itemCategory = ItemCategoryRepository.findById(ItemCategoryid).get();
        return itemCategory;

    }

}