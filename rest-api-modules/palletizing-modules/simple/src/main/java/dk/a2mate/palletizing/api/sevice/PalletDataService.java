package dk.a2mate.palletizing.api.sevice;

import java.util.List;

import dk.a2mate.palletizing.api.model.Pallet;

public interface PalletDataService {

	// find operation
	Pallet findById(String palletId);

	// Save operation
	Pallet savePallet(Pallet Pallet);

	// Read operation
	List<Pallet> fetchPalletList();

	// Update operation
	Pallet updatePallet(Pallet pallet, String palletId);

	// Delete operation
	void deletePalletById(String palletId);

}
