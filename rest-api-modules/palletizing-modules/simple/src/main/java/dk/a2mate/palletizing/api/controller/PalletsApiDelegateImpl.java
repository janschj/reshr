package dk.a2mate.palletizing.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.PalletsApiDelegate;
import dk.a2mate.palletizing.api.model.Pallet;
import dk.a2mate.palletizing.api.sevice.PalletDataService;

@Service
public class PalletsApiDelegateImpl implements PalletsApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(PalletsApiDelegateImpl.class);

	@Autowired
    private PalletDataService palletDataService;

	@Override
	public ResponseEntity<Void> createPallet(Pallet pallet) {
		LOGGER.info("createPallet id {}", pallet.getId());
		palletDataService.savePallet(pallet);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Pallet>> listPallets(Integer limit) {
		LOGGER.info("listPallets {}", limit);
		List<Pallet> pallets = new ArrayList<>();
		pallets.addAll(palletDataService.fetchPalletList());
		return new ResponseEntity<>(pallets, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Pallet> showPalletById(String palletId) {
		LOGGER.info("showPalletById {}", palletId);
		Pallet pallet = palletDataService.findById(palletId);
		return new ResponseEntity<>(pallet, HttpStatus.OK);
	}

}
