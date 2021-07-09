/**
 * 
 */
package dev.hupp.services;

import java.util.List;

import dev.hupp.models.Client;

/**
 * @author Jordan Hupp
 *
 */
public interface ClientService {
	//'Trivial' Services
	public Client getClient(int id);
	public List<Client> getAllClients();
	public Client addClient(Client c);
	public Client updateClient(Client update);
	public Client deleteClient(int id);
	
	//'Real' Services
	public boolean clientExists(int id);
	//Pretty sure these are optional/bonus and I'll play with them later.
//	public boolean login(String email, String password);
//	public boolean changePassword(String email, String oldPass, String newPass);
//	public boolean forgotPassword(String email, String newPass); //Should include pwHint?

	
}
