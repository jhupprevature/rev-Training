/**
 * 
 */
package dev.hupp.controllers;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

import dev.hupp.models.Account;
import dev.hupp.models.Client;
import dev.hupp.models.TransactionJson;
import dev.hupp.services.AccountService;
import dev.hupp.services.ClientService;
import io.javalin.http.Handler;

/**
 * @author Jordan Hupp
 *
 */
public class BankController {
	private Logger log = Logger.getLogger("Bank Logger");

	public AccountService as;
	public ClientService cs;
	Gson gson = new Gson();

	public BankController(AccountService as, ClientService cs) {
		this.as = as;
		this.cs = cs;
	}

	public Handler getAccount = (context) -> {
		String input1 = context.pathParam("client");
		String input2 = context.pathParam("id");
		int client;
		int id;

		try {
			client = Integer.parseInt(input1);
			id = Integer.parseInt(input2);

			Account account = as.getAccount(id);

			if (cs.clientExists(client) && client == account.getClient()) {
				context.result(gson.toJson(account));
				context.status(200);
				log.info("Account retrieved.");
			} else {
				context.result("{}");
				log.error("404: Client does not exist OR Client doesn't own Account.");
				context.status(404);
			}

		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler getAccountsByClient = (context) -> {
		String input = context.pathParam("client");
		int client;
		try {
			client = Integer.parseInt(input);

			if (cs.clientExists(client)) {

				List<Account> accounts = as.getAccountsByClient(client);

				double maxValue = Double.parseDouble(context.queryParam("amountLessThan", "100000000"));
				double minValue = Double.parseDouble(context.queryParam("amountGreaterThan", "0"));
				List<Account> paredAccounts = new ArrayList<Account>();

				for (Account a : accounts) {
					double balance = a.getBalance();

					if (balance < maxValue && balance > minValue) {
						paredAccounts.add(a);
					}
				}

				if (!paredAccounts.isEmpty()) {
					accounts = paredAccounts;
				}

				context.result(gson.toJson(accounts));
				log.info("Accounts retrieved.");

			} else {
				context.result("{}");
				log.error("404: Client does not exist.");
				context.status(404);
			}

		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler getAllAccounts = (context) -> {
		List<Account> accounts = as.getAllAccounts();
		context.result(gson.toJson(accounts));
		log.info("Accounts retrieved.");
	};

	public Handler addAccountByClientID = (context) -> {
		Account account = gson.fromJson(context.body(), Account.class);
		String input = context.pathParam("client");
		int client;

		try {
			client = Integer.parseInt(input);

			if (cs.clientExists(client)) {
				account.setClient(client);

				account = as.addAccount(account);
				
				log.info("Account created.");
				context.result(gson.toJson(account));
				context.status(201);
			} else {
				log.error("404: Client does not exist.");
				context.result("{}");
				context.status(404);
			}

		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler updateAccount = (context) -> {
		Account account = gson.fromJson(context.body(), Account.class);
		String input1 = context.pathParam("client");
		String input2 = context.pathParam("id");
		int client;
		int id;

		try {
			client = Integer.parseInt(input1);
			id = Integer.parseInt(input2);
			Account a = as.getAccount(id);

			if (a != null && client == a.getClient()) {
				account.setClient(client);
				account.setId(id);

				account = as.updateAccount(account);

				log.info("Account updated.");
				context.result(gson.toJson(account));
				context.status(200);
			} else {
				log.error("404: Account does not exist OR Client doesn't own Account.");
				context.result("{}");
				context.status(404);
			}
		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler deleteAccount = (context) -> {
		String input1 = context.pathParam("client");
		String input2 = context.pathParam("id");
		int client;
		int id;

		try {
			client = Integer.parseInt(input1);
			id = Integer.parseInt(input2);
			Account a = as.getAccount(id);

			if (a != null && client == a.getClient()) {
				Account account = as.deleteAccount(id);

				log.info("Account deleted.");
				context.result(gson.toJson(account));
				context.status(204);
			} else {
				context.result("{}");
				log.error("404: Account does not exist OR Client doesn't own Account.");
				context.status(404);
			}
		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler transferFunds = (context) -> {
		String input1 = context.pathParam("client");
		String input2 = context.pathParam("origin");
		int client;
		int origin;
		try {
			client = Integer.parseInt(input1);
			origin = Integer.parseInt(input2);

			Account acct1 = as.getAccount(origin);

			if (acct1 != null && client == acct1.getClient()) {

				TransactionJson tj = gson.fromJson(context.body(), TransactionJson.class);
				String type = tj.getTransaction();
				double amount = tj.getAmount();

				switch (type) {
				case "withdraw":
					if (acct1.getBalance() < amount) {
						log.error("Insufficient Balance.");
						context.status(422);
						break;
					}
					acct1 = as.withdraw(origin, amount);

					log.info("Transaction performed.");
					context.result(gson.toJson(acct1));
					break;
				case "deposit":
					acct1 = as.deposit(origin, amount);

					log.info("Transaction performed.");
					context.result(gson.toJson(acct1));
					break;
				case "transfer":
					String input3 = context.pathParam("destination");
					int destination = Integer.parseInt(input3);
					Account acct2 = as.getAccount(destination);

					if (acct2 != null && client == acct2.getClient()) {
						List<Account> accts = new ArrayList<Account>();

						if (acct1.getBalance() < amount) {
							log.error("Insufficient Balance.");
							context.status(422);
							break;
						}

						acct1 = as.withdraw(origin, amount);
						acct2 = as.deposit(destination, amount);

						accts.add(acct1);
						accts.add(acct2);

						log.info("Transaction performed.");
						context.result(gson.toJson(accts));
					} else {
						log.error("404: Account does not exist OR Client doesn't own Account.");
						context.result("{}");
						context.status(404);
					}
				}
			} else {
				log.error("404: Account does not exist OR Client doesn't own Account.");
				context.result("{}");
				context.status(404);
			}
		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler getAllClients = (context) -> {
		List<Client> clients = cs.getAllClients();

		if (clients == null) {
			log.error("500: No Clients found.");
			context.result("{}");
			context.status(500);
		} else {
			log.info("Clients retrieved.");
			context.result(gson.toJson(clients));
			context.status(200);
		}

	};

	public Handler getClientById = (context) -> {
		String input = context.pathParam("id");
		int id;

		try {
			id = Integer.parseInt(input);
			Client c = cs.getClient(id);

			if (c == null) {
				log.error("404: Client does not exist.");
				context.result("{}");
				context.status(404);
			} else {
				log.info("Client retrieved.");
				context.result(gson.toJson(c));
				context.status(200);
			}

		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler addClient = (context) -> {
		Client c = gson.fromJson(context.body(), Client.class);

		c = cs.addClient(c);
		if (c != null) {
			log.info("Client created.");
			context.result(gson.toJson(c));
			context.status(201);
		} else {
			log.error("500: Unable to create Client.");
			context.result("{}");
			context.status(500);
		}
	};

	public Handler updateClient = (context) -> {
		Client c = gson.fromJson(context.body(), Client.class);
		String input = context.pathParam("id");
		int id;

		try {
			id = Integer.parseInt(input);
			c.setId(id);
			c = cs.updateClient(c);

			if (c != null) {
				log.info("Client updated.");
				context.result(gson.toJson(c));
				context.status(200);
			} else {
				log.error("404: Client does not exist.");
				context.result("{}");
				context.status(404);
			}
		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};

	public Handler deleteClient = (context) -> {
		String input = context.pathParam("id");
		int id;

		try {
			id = Integer.parseInt(input);

			List<Account> clientAccounts = as.getAccountsByClient(id);

			for (Account a : clientAccounts) {
				as.deleteAccount(a.getId());
			}

			Client c = cs.deleteClient(id);

			if (c != null) {
				log.info("Client deleted.");
				context.result(gson.toJson(c));
				context.status(204);
			} else {
				log.error("404: Client does not exist.");
				context.result("{}");
				context.status(404);
			}
		} catch (NumberFormatException e) {
			log.trace(e.getStackTrace());
			context.status(400);
		}
	};
}
