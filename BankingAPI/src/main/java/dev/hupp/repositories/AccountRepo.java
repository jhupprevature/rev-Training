/**
 * 
 */
package dev.hupp.repositories;

import java.util.List;

import dev.hupp.models.Account;

/**
 * @author Jordan Hupp
 *
 */
public interface AccountRepo {
	public Account getAccount(int id);
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByClient(int clientID);
	public Account addAccount(Account a);
	public Account updateAccount(Account update);
	public Account deleteAccount(int id);
}
