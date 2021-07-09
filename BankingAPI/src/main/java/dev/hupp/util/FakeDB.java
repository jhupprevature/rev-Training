/**
 * 
 */
package dev.hupp.util;

import java.util.HashMap;
import java.util.Map;

import dev.hupp.models.Account;
import dev.hupp.models.CheckingAccount;
import dev.hupp.models.Client;
import dev.hupp.models.SavingsAccount;

/**
 * @author Jordan Hupp
 *
 */
public class FakeDB {
	public static Map<Integer, Client> clients = new HashMap<Integer, Client>();
	public static Map<Integer, Account> accounts = new HashMap<Integer, Account>();
	public static int idCountClient = 1;
	public static int idCountAccount = 100001;
	
	static {
		//TODO populate data
		Client c1 = new Client("Percival", "de Rolo", "ingenuity@whitestone.net", "d1pl0m4cY");
		Client c2 = new Client("Pike", "Trickfoot", "sarenreaismybestie@bramblewood.org", "L177l3M0nst4");
		Client c3 = new Client("Vex'ahlia", "de Rolo", "greyhunt@whitestone.net", "Tr1nk37");
		Client c4 = new Client("Grog", "Strongjaw", "beardo6@vasselheim.com", "ale");
		Client c5 = new Client("Scanlan", "Shorthalt", "TheMeatMan@ankharel.org", "BIGbysHand");
		// Gilmore
		// Vax'ildan
		// Keyleth
				
		clients.put(idCountClient++, c1);
		clients.put(idCountClient++, c2);
		clients.put(idCountClient++, c3);
		clients.put(idCountClient++, c4);
		clients.put(idCountClient++, c5);
		
		Account a1 = new CheckingAccount(1, 10_903.0);
		Account a2 = new SavingsAccount(1, 124_280.0);
		Account a3 = new SavingsAccount(2, 5_385.0);
		Account a4 = new CheckingAccount(3, 65_980.0);
		Account a5 = new CheckingAccount(3, 12_350.0);
		Account a6 = new SavingsAccount(3, 58_650.0);
		Account a7 = new CheckingAccount(4, 2_480.0);
		Account a8 = new SavingsAccount(5, 12_305.0);
		Account a9 = new SavingsAccount(5, 32_422.0);

		accounts.put(idCountAccount++, a1);
		accounts.put(idCountAccount++, a2);
		accounts.put(idCountAccount++, a3);
		accounts.put(idCountAccount++, a4);
		accounts.put(idCountAccount++, a5);
		accounts.put(idCountAccount++, a6);
		accounts.put(idCountAccount++, a7);
		accounts.put(idCountAccount++, a8);
		accounts.put(idCountAccount++, a9);
	}
}
