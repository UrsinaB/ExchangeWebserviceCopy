package ch.fhnw.crm.exchangeWebservice.data.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "itemCategories")
public class ItemCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;


    //enum for item category
    public enum CategoryName {
        ELECTRONICS, FURNITURE, BOOKS, FASHION, CHILDERN, PETS, HOUSEHOLD, OTHER
    }

    @NotEmpty(message = "Please provide a name for your item category")
    @Column(name = "categoryName")
    private String CategoryName;


    @Enumerated(EnumType.STRING)
    @Column (name = "categories")
    private CategoryName categoryName;


   @OneToMany(mappedBy = "itemCategory")
    private List<Item> items;

    public ItemCategory() {
    }

    public ItemCategory(Long categoryId, CategoryName categoryName, List<Item> items) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.items = items;
    }


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
    }



}
