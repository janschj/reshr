package dk.a2mate.palletizing.api.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.LayeredItem;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;
import dk.a2mate.palletizing.api.model.PalletizedPallet;

/*
 * Block stacking is identical rows on all layers
 */
public class RowStacking extends Stacking {

	private static final Logger LOGGER = LoggerFactory.getLogger(RowStacking.class);

	public RowStacking(Pallet pallet, Item item) {
		super(pallet, item);
	}

	private int maxItemOnYAxis(boolean rotated) {
		if(rotated) {
			return  Math.floorDiv(pallet.getWidth(), item.getLength());
		}  else {
			return Math.floorDiv(pallet.getWidth(), item.getWidth());
		}
	}
	
	private int maxItemsInHeight() {
		return Math.floorDiv(pallet.getMaxHeight(), item.getHeight());
	}

	private int maxItemOnXAxis(boolean rotated) {
		if(rotated) {
			return Math.floorDiv(pallet.getLength(), item.getWidth());
		}  else {
			return Math.floorDiv(pallet.getLength(), item.getLength());
		}
	}
	
	private int length(boolean rotated) {
		if(rotated) {
			return item.getWidth();
		}  else {
			return item.getLength();
		}
		
	}
	private int width(boolean rotated) {
		if(rotated) {
			return item.getLength();
		}  else {
			return item.getWidth();
		}
		
	}
	private int indentOnXAxis(boolean rotated) {
		return Math.floorDiv(pallet.getLength() - (maxItemOnXAxis(rotated) * length(rotated)), 2);
	}
	private int indentOnYAxis(boolean rotated) {
		return Math.floorDiv(pallet.getWidth() - (maxItemOnYAxis(rotated) * width(rotated)), 2);
	}
	
	private int maxItemOnPalletLayer(boolean rotated) {
			return maxItemOnXAxis(rotated) * maxItemOnYAxis(rotated);
	}
	

	private boolean isRotatedBest() {
		return maxItemOnPalletLayer(false) < maxItemOnPalletLayer(true);
	}

	/*
	 * ROW stack rotate layer after each layer
	 */
	public PalletLayer[] generateStacking() {
		LOGGER.debug("generateStacking()", pallet.getId());

		PalletizedPallet pp = new PalletizedPallet();
		pp.setNoOfItems(0);
		pp.setWeight(0);
		pp.setNoOfLayers(maxItemsInHeight());
		//
		LOGGER.debug("generateStacking() maxItemsInHeight() {}", maxItemsInHeight());
		
		PalletLayer[] layers = new PalletLayer[maxItemsInHeight()];
		for (int z = 0; z < maxItemsInHeight(); z++) {
			boolean rotated = (z % 2) == 1 ? true : false;
			PalletLayer layer = new PalletLayer();
			layer.setzCoordinate(pallet.getHeight() + (z * item.getHeight()));
			layer.setLayeredItems(getLayeredItems(rotated));
			layer.setWeight(item.getWeight() * maxItemOnXAxis(rotated) * indentOnYAxis(rotated));
			layer.setNoOfItems( maxItemOnXAxis(rotated) * indentOnYAxis(rotated));
			layers[z] = layer;
		}
		return layers;
	}
	
	private LayeredItem[][] getLayeredItems(boolean rotated) {
		LayeredItem[][] lis = new LayeredItem[maxItemOnXAxis(rotated)][maxItemOnYAxis(rotated)];
		for (int x = 0; x < maxItemOnXAxis(rotated); x++) {
			for (int y = 0; y < maxItemOnYAxis(rotated); y++) {
				LayeredItem li = new LayeredItem();
				li.setxCoordinate(indentOnXAxis(rotated) + (x * length(rotated)));
				li.setyCoordinate(indentOnYAxis(rotated)+ (y * width(rotated)));
				li.setRotated(rotated);
				lis[x][y] = li;				
			}
		}
		return lis;
	}

}