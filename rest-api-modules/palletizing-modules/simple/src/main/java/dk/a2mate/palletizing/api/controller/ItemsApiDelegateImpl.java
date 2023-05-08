package dk.a2mate.palletizing.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.ItemsApiDelegate;
import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.sevice.ItemDataService;

@Service
public class ItemsApiDelegateImpl implements ItemsApiDelegate {
	
	@Autowired
    private ItemDataService itemDataService;


	@Override
	public ResponseEntity<Void> createItem(Item item) {
		itemDataService.createItem(item);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Item> showItemById(String itemId) {
		Item item = itemDataService.findById(itemId);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Item>> listItems(Integer limit) {
		List<Item> items = new ArrayList<>();
		items.addAll(itemDataService.findAll());
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

}
