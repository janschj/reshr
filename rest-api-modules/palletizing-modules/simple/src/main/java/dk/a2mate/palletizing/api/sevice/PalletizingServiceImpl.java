package dk.a2mate.palletizing.api.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.internal.PatternFactory;
import dk.a2mate.palletizing.api.internal.Stacking;
import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.LayeredItem;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;
import dk.a2mate.palletizing.api.model.PalletizedItems;
import dk.a2mate.palletizing.api.model.PalletizedPallet;
import dk.a2mate.palletizing.api.model.PalletizingPattern;

@Service
public class PalletizingServiceImpl implements PalletizingService {
	
	@Autowired
    private ItemDataService itemDataService;

	@Autowired
    private PalletDataService palletDataService;

	@Override
	public PalletizedPallet generatePalletizedPallet(String palletId, String itemId, String pattern, String noOfItems, String noOfLayers) {
		System.out.println("pallet " + palletId);
		System.out.println("item " + itemId);
		Pallet pallet = palletDataService.findById(palletId);
		Item item = itemDataService.findById(itemId);
		System.out.println("pallet " + pallet.getId());
		System.out.println("item " + item.getId());		
		System.out.println("pattern " + pattern);	
		
		PalletizingPattern p = PalletizingPattern.valueOf(pattern);
		Stacking stacking = PatternFactory.getPattern(p, pallet, item);
		PalletLayer[] pl = stacking.generateStacking();		System.out.println("pl " + pl.length);	
		PalletizedPallet pp = new PalletizedPallet();
		pp.setNoOfItems(0);
		pp.setNoOfLayers(0);
		pp.setWeight(0);
		translateLayers(pp, pl);
		return pp;
	}
	
	private void translateLayers(PalletizedPallet pp, PalletLayer[] pls) {
		for(int i=0;i<pls.length;i++) {
			System.out.println("translate layer  " + i);	
			translateLayer(pp, pls[i]);
			pp.setNoOfItems(pp.getNoOfItems()+pls[i].getNoOfItems());
			pp.setWeight(pp.getWeight()+pls[i].getWeight());
			pp.setNoOfLayers(pp.getNoOfLayers() + 1);
		}
		System.out.println("translate layer  " + pp.getItems().size());	
	}
	private void translateLayer(PalletizedPallet pp, PalletLayer pls) {
		LayeredItem[][] lis = pls.getLayeredItems();
		for(int x=0;x<lis.length;x++) {
			System.out.println("translate layer x  " + x + " " + lis.length);	
			for(int y=0;y<lis[x].length;y++) {
				PalletizedItems item = new PalletizedItems();
				item.setRotate(lis[x][y].isRotated()? 0:1);
				item.setxAxis(lis[x][y].getxCoordinate());
				item.setyAxis(lis[x][y].getyCoordinate());
				item.setzAxis(pls.getzCoordinate());
				pp.addItemsItem(item);
			}
		}
		System.out.println("pp " + pp.getItems().size());	
	}

/*
 2. Stabilize Boxes
It’s a simple physics law: Place the heaviest boxes on the bottom of the pallet (the top layer should be full for steadiness)., but if there are not enough boxes to fully stack a pallet, place the last few along the outside edge.

3. Don’t Neglect Wide Gaps
Some wooden pallets have wide gaps between boards (around 4 inches). When you bridge this gap with a box, you create an overhang situation which reduces a box’s compression resistance potential.

4. Stack in Columns
In order to reduce the danger of damage and for greater stability, boxes should be stacked in columns with one box directly over the other. Also, stick items as close to each other as possible. Column stacks prove to be the best way to make pallets stable and safe for shipment.

5. Don’t Interlock Boxes
Interlocking can reduce strength by 50 percent and lead to freight damage. Since two-third of potential compression strength is in vertical edges and corners, it is important to stack boxes edge-to-edge and corner-to-corner.

https://www.webstaurantstore.com/article/716/pallet-stacking-patterns.html
 *
 */
}
