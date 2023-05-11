package dk.a2mate.palletizing.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.ItemsApiDelegate;
import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.sevice.ItemDataService;

@Service
public class ItemsApiDelegateImpl implements ItemsApiDelegate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemsApiDelegateImpl.class);

	@Autowired
    private ItemDataService itemDataService;


	@Override
	public ResponseEntity<Void> createItem(Item item) {
		LOGGER.info("createItem id {}", item.getId());
		itemDataService.saveItem(item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Item> showItemById(String itemId) {
		LOGGER.info("showItemById id {}", itemId);
		Item item = itemDataService.findById(itemId);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Item>> listItems(Integer limit) {
		LOGGER.info("listItems ");
		List<Item> items = itemDataService.fetchItemList();
		LOGGER.info("listItems {}", items.size());
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

}
