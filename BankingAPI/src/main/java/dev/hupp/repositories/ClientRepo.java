/**
 * 
 */
package dev.hupp.repositories;

import java.util.List;

import dev.hupp.models.Client;

/**
 * @author Jordan Hupp
 *
 */
public interface ClientRepo {
	public Client getClient(int id);
	public List<Client> getAllClients();
	public Client addClient(Client c);
	public Client updateClient(Client update);
	public Client deleteClient(int id);
}
