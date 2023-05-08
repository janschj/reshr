package dk.a2mate.palletizing.api.xternal;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.sevice.ItemDataService;

@Service
public class ItemDataServiceImpl implements ItemDataService {
	
	static HashMap<String, Item> itemStore = new HashMap<String, Item>();
	
	@Override
	public void createItem(Item item) {
		itemStore.putIfAbsent(item.getId(), item);
	}


	
	@Override
	public Item findById(String id) {
		return itemStore.get(id);
	}

	@Override
	public Set<Item> findAll() {
		
		if (itemStore.isEmpty()) {
			itemStore.putIfAbsent("EPX", getEPX());
			itemStore.putIfAbsent("EPX2", getEPX2());
		}
		
		 Set<Item> items = new TreeSet<>(new Comparator<Item>() {
		        @Override
		        public int compare(Item o1, Item o2) {
		            // Define comparing logic here
		            return o1.getName().compareTo(o2.getName());
		        }
			 
		    });
		
		items.addAll(itemStore.values());
		return items;
	}

	private Item getEPX() {
		Item b = new Item();
		b.setId("EPX");
		b.setHeight(500);
		b.setName("Epoxy pakke");
		b.setWeight(10);
		b.setWidth(350);
		b.setLength(450);
		return b;
	}
	
	private Item getEPX2() {
		Item b = new Item();
		b.setId("EPX2");
		b.setHeight(100);
		b.setName("Epoxy2 pakke");
		b.setWeight(15);
		b.setWidth(150);
		b.setLength(250);
		return b;
	}



}
