package dk.a2mate.palletizing.api.sevice;

import dk.a2mate.palletizing.api.model.PalletizedPallet;

public interface PalletizingService {
	
	PalletizedPallet generatePalletizedPallet(String palletId, String itemId, String pattern, String noOfItems, String noOfLayers);
}
