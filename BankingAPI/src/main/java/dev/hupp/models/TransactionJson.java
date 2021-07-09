/**
 * 
 */
package dev.hupp.models;

/**
 * @author Jordan Hupp
 *
 */
public class TransactionJson {
	private String transaction;
	private double amount;
	
	public TransactionJson() {
		super();
	}
	
	public TransactionJson(String transaction) {
		super();
		this.transaction = transaction;
	}

	/**
	 * @return the transaction
	 */
	public String getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
