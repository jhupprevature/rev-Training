package dev.hupp.models;

/**
 * @author Jordan Hupp
 *
 */
public class Account {
	//TODO (OPT) Make this class abstract
	protected int id;
	protected int client;
	protected double balance;

	/**
	 * 
	 */
	public Account() {
		super();
	}

	/**
	 * @param id
	 * @param client
	 * @param balance
	 */
	public Account(int id, int client, double balance) {
		super();
		this.id = id;
		this.client = client;
		this.balance = balance;
	}
	
	/**
	 * @param balance
	 */
	public Account(double balance) {
		super();
		this.balance = balance;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the client
	 */
	public int getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(int client) {
		this.client = client;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", client=" + client + ", balance=" + balance +  "]";
	}

}