package dk.a2mate.palletizing.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.a2mate.palletizing.api.PalletizingApiDelegate;
import dk.a2mate.palletizing.api.model.PalletizedPallet;
import dk.a2mate.palletizing.api.sevice.PalletizingService;
import dk.a2mate.palletizing.api.sevice.PalletizingServiceImpl;

@Service
public class PalletizingApiDelegateImpl implements PalletizingApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(PalletizingApiDelegateImpl.class);

	@Autowired
    private PalletizingService palletizingService;
	


	@Override
	public ResponseEntity<PalletizedPallet> showPalletizedPallet(String palletId, String itemId, String pattern,
			String noOfItems, String noOfLayers) {
		LOGGER.info("showPalletizedPallet{}{} ", palletId, itemId);
		PalletizedPallet pp = palletizingService.generatePalletizedPallet(palletId, itemId, pattern,
				noOfItems, noOfLayers);
		return new ResponseEntity<>(pp, HttpStatus.OK);
	}



}
