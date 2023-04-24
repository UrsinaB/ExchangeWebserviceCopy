package ch.fhnw.crm.exchangeWebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.crm.exchangeWebservice.business.service.ItemService;
import ch.fhnw.crm.exchangeWebservice.data.domain.Item;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ItemController {

    @Autowired
    private ItemService ItemService;

    //POST method to add a new item to the database and to relate it to a specific user

    @PostMapping(path = "/item/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Item> postItem(@RequestBody Item item, @PathVariable(value = "userId") String userId) {
        try {
            item = ItemService.saveItem(item);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
        return ResponseEntity.ok(item);
    }

    //PUT method to update an existing item belonging to a specific user

    @PutMapping(path = "/item/{itemId}/{userId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Item> putItem(@RequestBody Item item, @PathVariable(value = "itemId") String itemId, @PathVariable(value = "userId") String userId) {
        try {
            item.setItemId(Long.parseLong(itemId));
            item = ItemService.updateItem(item);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getConstraintViolations().iterator().next().getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
        }
        return ResponseEntity.accepted().body(item);
    }

    //GET method to retrieve all items from the database

    @GetMapping(path = "/item", produces = "application/json")
    public List<Item> getItems() {
        return ItemService.getAllItems();
    }

    //GET method to retrieve all items from the database belonging to a specific user

    @GetMapping(path = "/item/{userId}", produces = "application/json")
    public List<Item> getItems(@PathVariable(value = "userId") String userId) {
        return ItemService.getItemByUser(Long.parseLong(userId));
    }

    //GET method to retrieve a specific item from the database

    @GetMapping(path = "/item/{itemId}/{userId}", produces = "application/json")
    public ResponseEntity<Item> getItem(@PathVariable(value = "itemId") String itemId, @PathVariable(value = "userId") String userId) {
        Item item = null;
        try {
            item = ItemService.getItemById(Long.parseLong(itemId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(item);

    }

        // Get method for searching items by keyword

        @GetMapping(path = "/item/search/{keyword}", produces = "application/json")
        public ResponseEntity<List<Item>> searchItems(@RequestParam String keyword) {
            List<Item> items = null;
            try {
                items = ItemService.searchItems(keyword);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
            }
            return ResponseEntity.ok().body(items);
        }


    //DELETE method to delete a specific item from the database

    @DeleteMapping(path = "/item/{itemId}/{userId}", produces = "application/json") 
    public ResponseEntity<Item> deleteItem(@PathVariable(value = "itemId") String itemId, @PathVariable(value = "userId") String userId) {
        Item item = null;
        try {
            item = ItemService.deleteItem(Long.parseLong(itemId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return ResponseEntity.ok(item);
        
    }

    



    

   
    


    
    
}
