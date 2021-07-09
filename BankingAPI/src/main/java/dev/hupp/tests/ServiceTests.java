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
import dev.hupp.services.AccountService;
import dev.hupp.services.AccountServiceImpl;
import dev.hupp.services.ClientService;
import dev.hupp.services.ClientServiceImpl;

/**
 * @author Jordan Hupp
 *
 */
public class ServiceTests {

	@Test
	public void test() {
		ClientRepo cr = new ClientRepoDBImpl();
		ClientService csTest = new ClientServiceImpl(cr);
		
		AccountRepo ar = new AccountRepoDBImpl();
		AccountService asTest = new AccountServiceImpl(ar);

		Client c1test = new Client(9,"Taryon", "Darrington", 
				"darrington.brigade@deastok.co.dw", "DotyTakeThisDown");
		c1test = csTest.addClient(c1test);
		Account a1test = new Account(13, c1test.getId(), 53692.0);
		a1test.setClient(c1test.getId());
		a1test = asTest.addAccount(a1test);
		
		// Testing ClientServiceImpl
		assertNotNull("Testing that result of addClient is not null.", csTest.addClient(c1test));
		assertNotNull("Testing that result of clientExists is not null.", csTest.clientExists(c1test.getId()));
		assertNotNull("Testing that result of getClient is not null.", csTest.getClient(c1test.getId()));
		assertNotNull("Testing that result of updateClient is not null.", csTest.updateClient(c1test));
		assertNotNull("Testing that result of getAllClients is not null.", csTest.getAllClients());
		
		// Testing AccountServiceImpl
		assertNotNull("Testing that result of addAccount is not null.", asTest.addAccount(a1test));
		assertNotNull("Testing that result of checkAccountBalance is not null.", asTest.checkAccountBalance(a1test.getId()));
		assertNotNull("Testing that result of deposit is not null.", asTest.deposit(a1test.getId(), 250));
		assertNotNull("Testing that result of getAccount is not null.", asTest.getAccount(a1test.getId()));
		assertNotNull("Testing that result of getAccountsByClient is not null.", asTest.getAccountsByClient(a1test.getClient()));
		assertNotNull("Testing that result of getAllAccounts is not null.", asTest.getAllAccounts());
		assertNotNull("Testing that result of updateAccount is not null.", asTest.updateAccount(a1test));
		assertNotNull("Testing that result of withdraw is not null.", asTest.withdraw(a1test.getId(), 250));
		
		// Testing Delete services

		assertNotNull("Testing that result of deleteAccount is not null.", asTest.deleteAccount(a1test.getId()));
		assertNotNull("Testing that result of deleteClient is not null.", csTest.deleteClient(c1test.getId()));
	}

}
