/**
 * 
 */
package dev.hupp.services;

import java.util.List;

import dev.hupp.models.Account;
import dev.hupp.repositories.AccountRepo;

/**
 * @author Jordan Hupp
 *
 */
public class AccountServiceImpl implements AccountService {
	
	public AccountRepo ar;
	
	public AccountServiceImpl(AccountRepo ar) {
		this.ar = ar;
	}

	@Override
	public Account getAccount(int id) {
		return ar.getAccount(id);
	}

	@Override
	public List<Account> getAllAccounts() {
		return ar.getAllAccounts();
	}

	@Override
	public List<Account> getAccountsByClient(int client) {
		return ar.getAccountsByClient(client);
	}

	@Override
	public Account addAccount(Account a) {
		return ar.addAccount(a);
	}

	@Override
	public Account updateAccount(Account update) {
		return ar.updateAccount(update);
	}

	@Override
	public Account deleteAccount(int id) {
		return ar.deleteAccount(id);
	}

	@Override
	public double checkAccountBalance(int id) {
		return ar.getAccount(id).getBalance();
	}

	@Override
	public Account withdraw(int id, double amount) {
		Account a = ar.getAccount(id);
		double balance = a.getBalance();
		
		a.setBalance(balance-amount);
		a = this.updateAccount(a);
		return a;
	}

	@Override
	public Account deposit(int id, double amount) {
		Account a = ar.getAccount(id);
		double balance = a.getBalance();
		
		a.setBalance(balance+amount);
		a = this.updateAccount(a);
		return a;
	}

}
