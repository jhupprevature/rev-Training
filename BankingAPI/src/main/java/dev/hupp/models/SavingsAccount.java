package dev.hupp.models;
/**
 * @author Jordan Hupp
 *
 */
public class SavingsAccount extends Account {
	//TODO (OPT) Complete implementation of Different Account Types
	private double intrestRate;

	/**
	 * 
	 */
	public SavingsAccount() {
		super();
	}

	/**
	 * @param id
	 * @param client
	 * @param balance
	 */
	public SavingsAccount(int id, int client, double balance) {
		super();
		this.id = id;
		this.client = client;
		this.balance = balance;
	}
	
	/**
	 * @param client
	 * @param balance
	 */
	public SavingsAccount(int client, double balance) {
		super();
		this.balance = balance;
		this.client = client;
	}

	/**
	 * @return the intrestRate
	 */
	public double getIntrestRate() {
		return intrestRate;
	}

	/**
	 * @param intrestRate the intrestRate to set
	 */
	public void setIntrestRate(double intrestRate) {
		this.intrestRate = intrestRate;
	}

	@Override
	public String toString() {
		return "SavingsAccount [intrestRate=" + intrestRate + ", id=" + id + 
				", client" + client +  ", balance=" + balance + "]";
	}

}
