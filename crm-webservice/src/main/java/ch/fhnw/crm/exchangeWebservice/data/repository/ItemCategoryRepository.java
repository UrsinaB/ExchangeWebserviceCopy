package ch.fhnw.crm.exchangeWebservice.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory;


@Repository

public class ItemCategoryRepository {

    // find all item categories
    public List<ItemCategory> findAll() {
        return null;
    }


    // find item category by id
    public ItemCategory findById(Long id) {
        return null;
    }

    // find item category by name
    public ItemCategory findByName(String name) {
        return null;
    }

    // delete item category by id
    public void deleteById(Long id) {
    }

    // delete item category by name
    public void deleteByName(String name) {
    }
    
}
