package dk.a2mate.palletizing.api.internal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;

public class RowStackingTest {

	RowStacking stacking;
	Pallet pallet;
	Item item;

	@BeforeEach
	void setUp() {
		pallet = new Pallet();
		pallet.setHeight(20);
		pallet.setLength(1200);
		pallet.setMaxHeight(1500);
		pallet.setMaxLoad(1400);
		pallet.setWeight(12);
		pallet.setWidth(800);
		item = new Item();
		stacking = new RowStacking(pallet, item);
	}

	@Test
	@DisplayName("Item larger than maxHeight")
	void testHightWithTooBigItem() {
		item.setHeight(1501);
		item.setLength(1150);
		item.setWeight(10);
		item.setWidth(750);
		PalletLayer[] layer = stacking.generateStacking();
        assertEquals(0,layer.length);  
	}
	
	@Test
	@DisplayName("One item works")
	void testHightWithOneItem() {
		item.setHeight(1400);
		item.setLength(1150);
		item.setWeight(10);
		item.setWidth(750);
		PalletLayer[] layer = stacking.generateStacking();
        assertEquals(1,layer.length);  
	}
	@Test
	@DisplayName("MaxHeight reached")
	void testHightWithTwoItem() {
		item.setHeight(740);
		item.setLength(1150);
		item.setWeight(10);
		item.setWidth(750);
		PalletLayer[] layer = stacking.generateStacking();
        assertEquals(2,layer.length);  
	}
	
	@Test
	@DisplayName("2 layer is rotated")
	void testRotation() {
		item.setHeight(500);
		item.setLength(800);
		item.setWeight(10);
		item.setWidth(500);
		PalletLayer[] layer = stacking.generateStacking();
        assertTrue(layer[1].getLayeredItems()[0][0].isRotated());  
	}
	
	@Test
	@DisplayName("Null in item parameter is not valid")
	void testPalletForNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new BlockStacking(null, new Item());
		});
	}

	@Test
	@DisplayName("Null in item parameter is not valid")
	void testItemForNull() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new BlockStacking(new Pallet(), null);
		});
	}
}
