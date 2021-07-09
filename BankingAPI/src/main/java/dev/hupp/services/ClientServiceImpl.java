/**
 * 
 */
package dev.hupp.services;

import java.util.List;

import dev.hupp.models.Client;
import dev.hupp.repositories.ClientRepo;

/**
 * @author Jordan Hupp
 *
 */
public class ClientServiceImpl implements ClientService {
	
	public ClientRepo cr;
	
	public ClientServiceImpl(ClientRepo cr) {
		this.cr = cr;
	}
	
	@Override
	public Client getClient(int id) {
		return cr.getClient(id);
	}

	@Override
	public List<Client> getAllClients() {
		return cr.getAllClients();
	}

	@Override
	public Client addClient(Client c) {
		return cr.addClient(c);
	}

	@Override
	public Client updateClient(Client update) {
		return cr.updateClient(update);
	}

	@Override
	public Client deleteClient(int id) {
		return cr.deleteClient(id);
	}
	
	@Override
	public boolean clientExists(int id) {

		Client c = cr.getClient(id);

		if (c == null) { return false;
		} else { return true; }
	}
}
