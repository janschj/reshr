package dk.a2mate.palletizing.api.sevice;

import java.util.Set;

import dk.a2mate.palletizing.api.model.Item;

public interface ItemDataService {
	
	Set<Item> findAll();
	Item findById(String id);
	void createItem(Item item);

}
