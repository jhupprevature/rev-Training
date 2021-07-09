/**
 * 
 */
package dev.hupp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.hupp.models.Client;
import dev.hupp.util.JDBCConnection;

/**
 * @author Jordan Hupp
 *
 */
public class ClientRepoDBImpl implements ClientRepo {
	
	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Client getClient(int id) {
		String sql = "SELECT * FROM clients WHERE id = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			//TODO verify intended result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Client> getAllClients() {
		String sql = "SELECT * FROM clients";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Client> clients = new ArrayList<Client>();
			while(rs.next()) {
				clients.add(buildClient(rs));
			}
			
			return clients;
		} catch (SQLException e) {
			// TODO verify intended result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client addClient(Client c) {
		String sql = "INSERT INTO clients VALUES (default, ?, ?, ?, ?) "
				+ "RETURNING *";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getFirstName());
			ps.setString(2, c.getLastName());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return buildClient(rs);
			}
		} catch (SQLException e) {
			// TODO verify desired result
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Client updateClient(Client update) {
		String sql = "UPDATE clients SET first_name=?, last_name=?, email=?, "
				+ "password=? WHERE id=? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, update.getFirstName());
			ps.setString(2, update.getLastName());
			ps.setString(3, update.getEmail());
			ps.setString(4, update.getPassword());
			ps.setInt(5, update.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return buildClient(rs);
			}
			
		} catch (SQLException e) {
			//TODO verify desired result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Client deleteClient(int id) {
		String sql = "DELETE FROM clients WHERE id = ? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return buildClient(rs);
			}
			
		} catch (SQLException e) {
			//TODO verify desired result
			e.printStackTrace();
		}
		return null;
	}
	
	private Client buildClient(ResultSet rs) throws SQLException {
		Client c = new Client();
		c.setId(rs.getInt("id"));
		c.setEmail(rs.getString("email"));
		c.setFirstName(rs.getString("first_name"));
		c.setLastName(rs.getString("last_name"));
		c.setPassword(rs.getString("password"));
		
		return c;
	}
}
