/**
 * 
 */
package dev.hupp.app;

import dev.hupp.controllers.BankController;
import dev.hupp.repositories.AccountRepo;
import dev.hupp.repositories.AccountRepoDBImpl;
import dev.hupp.repositories.ClientRepo;
import dev.hupp.repositories.ClientRepoDBImpl;
import dev.hupp.services.AccountService;
import dev.hupp.services.AccountServiceImpl;
import dev.hupp.services.ClientService;
import dev.hupp.services.ClientServiceImpl;
import io.javalin.Javalin;

/**
 * @author Jordan Hupp
 *
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Javalin app = Javalin.create();
		
		establishRoutes(app);
		
		app.start(7000);

	}
	
	/**
	 * 
	 * @param app
	 */
	private static void establishRoutes(Javalin app) {
		
		ClientRepo cr = new ClientRepoDBImpl();
		ClientService cs = new ClientServiceImpl(cr);
		
		AccountRepo ar = new AccountRepoDBImpl();
		AccountService as = new AccountServiceImpl(ar);
		
		BankController bc = new BankController(as, cs);
		
		app.get("/clients", bc.getAllClients);
		app.get("/clients/:id", bc.getClientById);
		app.get("/clients/:client/accounts/", bc.getAccountsByClient);
		app.get("/clients/:client/accounts/:id", bc.getAccount);
		app.get("/accounts", bc.getAllAccounts);
		
		app.post("/clients", bc.addClient);
		app.post("/clients/:client/accounts/", bc.addAccountByClientID);
//		app.post("/accounts", ac.addAccountByClientEmail);
		
		app.put("/clients/:id", bc.updateClient);
		app.put("/clients/:client/accounts/:id", bc.updateAccount);
		
		app.delete("/clients/:id", bc.deleteClient);
		app.delete("/clients/:client/accounts/:id", bc.deleteAccount);
		
		app.patch("/clients/:client/accounts/:origin", bc.transferFunds);
		app.patch("/clients/:client/accounts/:origin/transfer/:destination", bc.transferFunds);
		
		
	}
	

}
