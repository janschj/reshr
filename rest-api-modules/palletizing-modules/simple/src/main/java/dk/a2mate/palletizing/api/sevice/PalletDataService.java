package dk.a2mate.palletizing.api.sevice;

import java.util.Set;

import dk.a2mate.palletizing.api.model.Pallet;

public interface PalletDataService {
	
	Set<Pallet> findAll();
	Pallet findById(String id);
	void createPallet(Pallet pallet);

}
