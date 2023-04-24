

package ch.fhnw.crm.exchangeWebservice.data.domain;

import ch.fhnw.crm.exchangeWebservice.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import ch.fhnw.crm.exchangeWebservice.data.domain.*;

//create a new Item category

public class TestData {

    public static ItemCategory createItemCategory() {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(1);
        itemCategory.setCategoryName("Electronics");

        return itemCategory;
    }

    // create another item category

    public static ItemCategory createItemCategory2() {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(2);
        itemCategory.setCategoryName("Clothes");

        return itemCategory;
    }

    // create the item category Sports

    public static ItemCategory createItemCategory3() {
        ItemCategory itemCategory = new ItemCategory();
        itemCategory.setCategoryId(3);
        itemCategory.setCategoryName("Sports");

        return itemCategory;
    }



// create a new user


    public static User createUser() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("John");
        user.setPassword("123");
        user.setEmail("John84@tbk.com");

        return user;
    }

    // create another user

    public static User createUser2() {
        User user = new User();
        user.setUserId(2);
        user.setUsername("Max");
        user.setPassword("1223");
        user.setEmail("MaxMueller@dkak.ch");

        return user;
    }


    // create a new item
    public static Item createItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setTitle("Bike");
        item.setDescription("A nice bike");
        item.setListingDate("2021-05-05");
        item.setItemStatus(ItemStatus.AVAILABLE);
        item.setUser(createUser());


        return item;
    }


    // create another item

    public static Item createItem2() {
        Item item = new Item();
        item.setItemId(2);
        item.setTitle("Shirt");
        item.setDescription("A nice shirt");
        item.setListingDate("2021-05-05");
        item.setItemStatus(ItemStatus.AVAILABLE);
        item.setUser(createUser2());
        item.setItemCategory(createItemCategory2());
        return item;

}

}



   

    