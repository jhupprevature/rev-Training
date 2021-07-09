/**
 * 
 */
package dev.hupp.models;

/**
 * @author Jordan Hupp
 *
 */
public class Client {
	
	int id;
	String firstName;
	String lastName;
	String email;
	String password;

	/**
	 * 
	 */
	public Client() {
		super();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Client(int id, String firstName, String lastName, String email, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Client(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		//TODO refactor output of accountList
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + "]";
	}
}

//TODO Imagine a world where this is useful...
//class Address {
//	int streetNumber;
//	String streetName;
//	String line2;
//	String city;
//	String state;
//	int zipCode;
//	
//	/**
//	 * 
//	 */
//	public Address() {
//		super();
//	}
//
//	/**
//	 * @param streetNumber
//	 * @param streetName
//	 * @param line2
//	 * @param city
//	 * @param state
//	 * @param zipCode
//	 */
//	public Address(int streetNumber, String streetName, String line2, String city, String state, int zipCode) {
//		super();
//		this.streetNumber = streetNumber;
//		this.streetName = streetName;
//		this.line2 = line2;
//		this.city = city;
//		this.state = state;
//		this.zipCode = zipCode;
//	}
//
//	/**
//	 * @param streetNumber
//	 * @param streetName
//	 * @param city
//	 * @param state
//	 * @param zipCode
//	 */
//	public Address(int streetNumber, String streetName, String city, String state, int zipCode) {
//		super();
//		this.streetNumber = streetNumber;
//		this.streetName = streetName;
//		this.city = city;
//		this.state = state;
//		this.zipCode = zipCode;
//	}
//
//	/**
//	 * @return the streetNumber
//	 */
//	public int getStreetNumber() {
//		return streetNumber;
//	}
//
//	/**
//	 * @param streetNumber the streetNumber to set
//	 */
//	public void setStreetNumber(int streetNumber) {
//		this.streetNumber = streetNumber;
//	}
//
//	/**
//	 * @return the streetName
//	 */
//	public String getStreetName() {
//		return streetName;
//	}
//
//	/**
//	 * @param streetName the streetName to set
//	 */
//	public void setStreetName(String streetName) {
//		this.streetName = streetName;
//	}
//
//	/**
//	 * @return the line2
//	 */
//	public String getLine2() {
//		return line2;
//	}
//
//	/**
//	 * @param line2 the line2 to set
//	 */
//	public void setLine2(String line2) {
//		this.line2 = line2;
//	}
//
//	/**
//	 * @return the city
//	 */
//	public String getCity() {
//		return city;
//	}
//
//	/**
//	 * @param city the city to set
//	 */
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	/**
//	 * @return the state
//	 */
//	public String getState() {
//		return state;
//	}
//
//	/**
//	 * @param state the state to set
//	 */
//	public void setState(String state) {
//		this.state = state;
//	}
//
//	/**
//	 * @return the zipCode
//	 */
//	public int getZipCode() {
//		return zipCode;
//	}
//
//	/**
//	 * @param zipCode the zipCode to set
//	 */
//	public void setZipCode(int zipCode) {
//		this.zipCode = zipCode;
//	}
//
//	@Override
//	public String toString() {
//		//TODO refactor this output
//		return "Address [streetNumber=" + streetNumber + ", streetName=" + streetName + ", line2=" + line2 + ", city="
//				+ city + ", state=" + state + ", zipCode=" + zipCode + "]";
//	}
//	
//}