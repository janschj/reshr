package dk.a2mate.palletizing.api.internal;

import javax.validation.constraints.NotNull;

import dk.a2mate.palletizing.api.model.Item;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.model.PalletLayer;


public abstract class Stacking {
	
	protected Pallet pallet;
	protected Item item;
	
	protected Stacking(Pallet pallet, Item item) {
		if(item == null || pallet == null) {
			throw new IllegalArgumentException();
		}
		this.pallet = pallet;
		this.item = item;
	}

	public abstract PalletLayer[] generateStacking();

}
