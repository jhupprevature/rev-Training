/**
 * 
 */
package dev.hupp.tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import dev.hupp.models.Account;
import dev.hupp.models.Client;
import dev.hupp.repositories.AccountRepo;
import dev.hupp.repositories.AccountRepoDBImpl;
import dev.hupp.repositories.ClientRepo;
import dev.hupp.repositories.ClientRepoDBImpl;

/**
 * @author Jordan Hupp
 *
 */
public class RepoTests {

	@Test
	public void test() {
		ClientRepo crTest = new ClientRepoDBImpl();
		
		AccountRepo arTest = new AccountRepoDBImpl();

		Client c1test = new Client("Taryon", "Darrington", 
				"darrington.brigade@deastok.co.dw", "DotyTakeThisDown");
		c1test = crTest.addClient(c1test);
		Account a1test = new Account(53692.0);
		a1test.setClient(c1test.getId());
		a1test = arTest.addAccount(a1test);
		
		
		// Testing ClientRepoImpl
		assertNotNull("Testing that result of addClient is not null.", crTest.addClient(c1test));
		assertNotNull("Testing that result of getClient is not null.", crTest.getClient(c1test.getId()));
		assertNotNull("Testing that result of updateClient is not null.", crTest.updateClient(c1test));
		assertNotNull("Testing that result of getAllClients is not null.", crTest.getAllClients());
		
		// Testing AccountServiceImpl
		assertNotNull("Testing that result of addAccount is not null.", arTest.addAccount(a1test));
		assertNotNull("Testing that result of getAccount is not null.", arTest.getAccount(a1test.getId()));
		assertNotNull("Testing that result of getAccountsByClient is not null.", arTest.getAccountsByClient(a1test.getClient()));
		assertNotNull("Testing that result of getAllAccounts is not null.", arTest.getAllAccounts());
		assertNotNull("Testing that result of updateAccount is not null.", arTest.updateAccount(a1test));
		
		// Testing Delete services
		assertNotNull("Testing that result of deleteAccount is not null.", arTest.deleteAccount(a1test.getId()));
		assertNotNull("Testing that result of deleteClient is not null.", crTest.deleteClient(c1test.getId()));
	}

}
