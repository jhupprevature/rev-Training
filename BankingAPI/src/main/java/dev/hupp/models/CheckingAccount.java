/**
 * 
 */
package dev.hupp.models;

/**
 * @author Jordan Hupp
 *
 */
public class CheckingAccount extends Account {

	/**
	 * 
	 */
	public CheckingAccount() {
		super();
	}

	/**
	 * @param id
	 * @param client
	 * @param balance
	 */
	public CheckingAccount(int id, int client, double balance) {
		super();
		this.id = id;
		this.client = client;
		this.balance = balance;
	}
	
	

	/**
	 * @param client
	 * @param balance
	 */
	public CheckingAccount(int client, double balance) {
		super();
		this.balance = balance;
		this.client = client;
	}

}
