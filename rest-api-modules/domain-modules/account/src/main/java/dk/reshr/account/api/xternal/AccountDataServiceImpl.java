package dk.reshr.account.api.xternal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dk.reshr.account.api.model.Account;
import dk.reshr.account.api.repository.AccountDao;
import dk.reshr.account.api.repository.AccountRepository;
import dk.reshr.account.api.sevice.AccountDataService;

@Service
public class AccountDataServiceImpl implements AccountDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDataServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findById(String accountId) {
		LOGGER.debug("findById {}", accountId);
		AccountDao dao = accountRepository.findById(accountId).get();
		return toAccount(dao);
	}

	@Override
	public Account saveAccount(Account account) {
		AccountDao d = new AccountDao();
		d.setId(account.getId());
		d.setName(account.getName());
		accountRepository.save(d);
		return account;
	}

	@Override
	public List<Account> fetchAccountList() {
		LOGGER.debug("toPallets {}");
		List<AccountDao> accounts = new ArrayList<AccountDao>();
        Iterable<AccountDao> iter = accountRepository.findAll();
        iter.forEach((p) -> {
    		LOGGER.info("fetchPalletList() --");

            accounts.add(p);
        });
		return toAccounts(accounts);
	}

	@Override
	public Account updateAccount(Account account, String accountId) {
		AccountDao d = accountRepository.findById(accountId).get();
		d.setName(account.getName());

		return account;
	}

	@Override
	public void deleteAccountById(String accountId) {
		accountRepository.deleteById(accountId);
	}

	private List<Account> toAccounts(List<AccountDao> accounts) {
		LOGGER.info("toPallets {}", accounts.size());
		List<Account> rtn = accounts.stream().map(p -> toAccount(p)).collect(Collectors.toList());
		return rtn;
	}

	private Account toAccount(AccountDao id) {
		LOGGER.info("toPallet {}", id.getId());
		Account d = new Account();
		d.setId(id.getId());
		d.setName(id.getName());
		return d;
	}

}
