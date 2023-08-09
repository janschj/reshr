package dk.reshr.account.api.sevice;

import java.util.List;

import dk.reshr.account.api.model.Account;

public interface AccountDataService {

	// find operation
	Account findById(String accountId);

	// Save operation
	Account saveAccount(Account Pallet);

	// Read operation
	List<Account> fetchAccountList();

	// Update operation
	Account updateAccount(Account account, String accountId);

	// Delete operation
	void deleteAccountById(String accountId);

}
