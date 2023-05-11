package dk.a2mate.palletizing.api.sevice;

import java.util.List;
import java.util.Set;

import dk.a2mate.palletizing.api.model.Item;

public interface ItemDataService {
	
	  // find operation
	Item findById(String itemId);
	
	  // Save operation
	Item saveItem(Item item);
  
    // Read operation
    List<Item> fetchItemList();
  
    // Update operation
    Item updateItem(Item item, String itemId);
  
    // Delete operation
    void deleteItemById(String itemId);

}
