package dk.a2mate.palletizing.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.PalletsApiDelegate;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.sevice.PalletDataService;

@Service
public class PalletsApiDelegateImpl implements PalletsApiDelegate {


	@Autowired
    private PalletDataService palletDataService;

	@Override
	public ResponseEntity<Void> createPallet(Pallet pallet) {
		palletDataService.createPallet(pallet);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Pallet>> listPallets(Integer limit) {
		List<Pallet> pallets = new ArrayList<>();
		pallets.addAll(palletDataService.findAll());
		return new ResponseEntity<>(pallets, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pallet> showPalletById(String palletId) {
		Pallet pallet = palletDataService.findById(palletId);
		return new ResponseEntity<>(pallet, HttpStatus.OK);
	}

}
