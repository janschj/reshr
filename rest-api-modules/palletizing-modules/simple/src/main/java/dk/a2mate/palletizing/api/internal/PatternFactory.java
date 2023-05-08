package dk.a2mate.palletizing.api.internal;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletizingPattern;

public class PatternFactory {
	
	   public static Stacking getPattern(PalletizingPattern p, Pallet pallet, Item item){
	      if(p == null){
	         return new BlockStacking(pallet, item);
	      }		
			switch(p) {
			case BLOCK:
				return new BlockStacking(pallet, item);
			case ROW:
				return new BlockStacking(pallet, item);
			default:
				return new BlockStacking(pallet, item);
		}	   }

}
