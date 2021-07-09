/**
 * 
 */
package dev.hupp.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.hupp.models.Account;
import dev.hupp.util.FakeDB;

/**
 * @author Jordan Hupp
 *
 */
public class AccountRepoImpl implements AccountRepo {

	/**
	 * 
	 */
	public AccountRepoImpl() {
		super();
	}

	@Override
	public Account getAccount(int id) {
		return FakeDB.accounts.get(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		Set<Integer> keys = FakeDB.accounts.keySet();
		
		keys.forEach(key -> accountList.add(FakeDB.accounts.get(key)));
		return accountList;
	}
	
	@Override
	public List<Account> getAccountsByClient(int clientID) {
		List<Account> accountList = new ArrayList<Account>();
		Set<Integer> keys = FakeDB.accounts.keySet();
		
		keys.forEach(key -> {
			Account account = FakeDB.accounts.get(key);
			if (clientID == account.getClient())
				accountList.add(account);
		});
		return accountList;
	}

	@Override
	public Account addAccount(Account a) {
		a.setId(FakeDB.idCountAccount++);
		
		FakeDB.accounts.put(a.getId(), a);
		return a;
	}

	@Override
	public Account updateAccount(Account update) {
		FakeDB.accounts.replace(update.getId(), update);
		return update;
	}

	@Override
	public Account deleteAccount(int id) {
		return FakeDB.accounts.remove(id);
	}

}
