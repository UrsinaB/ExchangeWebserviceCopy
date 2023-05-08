package ch.fhnw.crm.exchangeWebservice.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory;
import ch.fhnw.crm.exchangeWebservice.data.domain.ItemCategory.CategoryName;


@Repository

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {

    // find all item categories
    public  List<ItemCategory> findAll();
    
    // find item category by id
    public Optional<ItemCategory> findById(Long id);

    // find item category by name
    public ItemCategory findByCategoryName(CategoryName categoryName);

    // delete item category by id
    public void deleteById(Long id);

}
    

