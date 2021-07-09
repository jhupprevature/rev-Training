/**
 * 
 */
package dev.hupp.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import dev.hupp.models.Client;
import dev.hupp.util.FakeDB;

/**
 * @author Jordan Hupp
 *
 */
public class ClientRepoImpl implements ClientRepo {

	/**
	 * 
	 */
	public ClientRepoImpl() {
		super();
	}

	@Override
	public Client getClient(int id) {
		return FakeDB.clients.get(id);
	}

	@Override
	public List<Client> getAllClients() {
		List<Client> clientList = new ArrayList<Client>();
		Set<Integer> keys = FakeDB.clients.keySet();
		
		keys.forEach(key -> clientList.add(FakeDB.clients.get(key)));
		return clientList;
	}

	@Override
	public Client addClient(Client c) {
		c.setId(FakeDB.idCountClient++);
		
		FakeDB.clients.put(c.getId(), c);
		return c;
	}

	@Override
	public Client updateClient(Client update) {
		FakeDB.clients.replace(update.getId(), update);
		return update;
	}

	@Override
	public Client deleteClient(int id) {
		return FakeDB.clients.remove(id);
	}

}
