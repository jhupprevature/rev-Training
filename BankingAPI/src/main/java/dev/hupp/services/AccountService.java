/**
 * 
 */
package dev.hupp.services;

import java.util.List;

import dev.hupp.models.Account;

/**
 * @author Jordan Hupp
 *
 */
public interface AccountService {
	//'Trivial' services
	public Account getAccount(int id);
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByClient(int client);
	public Account addAccount(Account a);
	public Account updateAccount(Account update);
	public Account deleteAccount(int id);
	
	//'Real' services
	//TODO double check all these services are needed
	public double checkAccountBalance(int id);
	public Account withdraw(int id, double amount);
	public Account deposit(int id, double amount);
}
