package dk.a2mate.palletizing.api.internal;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.LayeredItem;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;
import dk.a2mate.palletizing.api.model.PalletizedPallet;

/*
 * Block stacking is identical rows on all layers
 */
public class BlockStacking extends Stacking {


	public BlockStacking(Pallet pallet, Item item) {
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
	 * BLOCK stack all layers identical
	 */
	public PalletLayer[] generateStacking() {
		System.out.println("BLOCK pattern");
		boolean rotated = isRotatedBest();
		System.out.println("rotated " + rotated);

		PalletizedPallet pp = new PalletizedPallet();
		pp.setNoOfItems(0);
		pp.setWeight(0);
		pp.setNoOfLayers(maxItemsInHeight());
		//
		System.out.println("z " + maxItemsInHeight());
		System.out.println("x " + maxItemOnXAxis(rotated));
		System.out.println("y " + maxItemOnYAxis(rotated));
		System.out.println("x sp " + indentOnXAxis(rotated));
		System.out.println("y sp " +  indentOnYAxis(rotated));
		
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
		PalletLayer[] layers = new PalletLayer[maxItemsInHeight()];
		System.out.println("maxItemsInHeight " + maxItemsInHeight());
		for (int z = 0; z < maxItemsInHeight(); z++) {
			PalletLayer layer = new PalletLayer();
			layer.setzCoordinate(pallet.getHeight() + (z * item.getHeight()));
			layer.setLayeredItems(lis);
			layer.setWeight(item.getWeight() * maxItemOnXAxis(rotated) * indentOnYAxis(rotated));
			layer.setNoOfItems( maxItemOnXAxis(rotated) * indentOnYAxis(rotated));
			layers[z] = layer;
		}
		return layers;
	}

}
