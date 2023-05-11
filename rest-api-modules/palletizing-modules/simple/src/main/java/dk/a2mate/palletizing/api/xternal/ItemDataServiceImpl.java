package dk.a2mate.palletizing.api.xternal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.repository.ItemDao;
import dk.a2mate.palletizing.api.repository.ItemRepository;
import dk.a2mate.palletizing.api.sevice.ItemDataService;

@Service
public class ItemDataServiceImpl implements ItemDataService {
	

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemDataServiceImpl.class);

	@Autowired
    private ItemRepository itemRepository;
  

	@Override
	public Item findById(String itemId) {
		LOGGER.info("findbyid");
		ItemDao d
        = itemRepository.findById(itemId)
              .get();
		return toItem(d);
	}

	@Override
	public Item saveItem(Item item) {
		LOGGER.info("saveitem");
		ItemDao d = new ItemDao();
		d.setId(item.getId());
		d.setHeight(item.getHeight());
		d.setLength(item.getLength());
		d.setName(item.getName());
		d.setWeight(item.getWeight());
		d.setWidth(item.getWidth());
		itemRepository.save(d);
		return item;
	}
	
	@Override
	public List<Item> fetchItemList() {
		LOGGER.info("fetchItemList() --");
		List<ItemDao> rtn = new ArrayList<ItemDao>();
		// get or retrieve all products
        Iterable<ItemDao> items = itemRepository.findAll();
        items.forEach((p) -> {
    		LOGGER.info("fetchItemList() --");

            rtn.add(p);
        });
		LOGGER.info("fetchItemList() -- {}", rtn.size());
		List<Item> i = toItems(rtn);
		LOGGER.info("fetchItemList() -- {}", i.size());
		return i;
	}
	
	@Override
	public Item updateItem(Item item, String itemId) {
		ItemDao d
        = itemRepository.findById(itemId)
              .get();
		d.setHeight(item.getHeight());
		d.setLength(item.getLength());
		d.setName(item.getName());
		d.setWeight(item.getWeight());
		d.setWidth(item.getWidth());
		
		return item;
	}
	
	@Override
	public void deleteItemById(String itemId) {
		itemRepository.deleteById(itemId);		
	}

	private List<Item> toItems(List<ItemDao> items) {
		LOGGER.info("toItems -- {}", items.size());
		List<Item> rtn = items.stream().map(p -> toItem(p))
        .collect(Collectors.toList());
		return rtn;
	}
	private Item toItem(ItemDao id) {
		Item d = new Item();
		d.setId(id.getId());
		d.setHeight(id.getHeight());
		d.setLength(id.getLength());
		d.setName(id.getName());
		d.setWeight(id.getWeight());
		d.setWidth(id.getWidth());
		return d;
	}
	


}
