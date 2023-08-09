package dk.reshr.account.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import dk.reshr.account.api.AccountsApiDelegate;
import dk.reshr.account.api.model.Account;
import dk.reshr.account.api.sevice.AccountDataService;

@Service
public class AccountsApiDelegateImpl implements AccountsApiDelegate {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountsApiDelegateImpl.class);

	@Autowired
    private AccountDataService palletDataService;

	@Override
	public ResponseEntity<Void> createAccount(Account pallet) {
		LOGGER.info("createPallet id {}", pallet.getId());
		palletDataService.saveAccount(pallet);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<Account>> listAccounts(Integer limit) {
		LOGGER.info("listPallets {}", limit);
		List<Account> pallets = new ArrayList<>();
		pallets.addAll(palletDataService.fetchAccountList());
		return new ResponseEntity<>(pallets, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Account> showAccountById(String palletId) {
		LOGGER.info("showPalletById {}", palletId);
		Account pallet = palletDataService.findById(palletId);
		return new ResponseEntity<>(pallet, HttpStatus.OK);
	}

}
