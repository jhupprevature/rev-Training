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

import dev.hupp.models.Account;
import dev.hupp.util.JDBCConnection;

/**
 * @author Jordan Hupp
 *
 */
public class AccountRepoDBImpl implements AccountRepo {

	public static Connection conn = JDBCConnection.getConnection();

	@Override
	public Account getAccount(int id) {
		String sql = "SELECT * FROM accounts WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			// TODO verify intended result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		String sql = "SELECT * FROM accounts";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Account> accts = new ArrayList<Account>();
			while (rs.next()) {
				accts.add(buildAccount(rs));
			}
			
			return accts;
		} catch (SQLException e) {
			// TODO verify intended result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> getAccountsByClient(int clientID) {
		String sql = "SELECT * FROM accounts WHERE client = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, clientID);
			
			ResultSet rs = ps.executeQuery();
			
			List<Account> accts = new ArrayList<Account>();
			while (rs.next()) {
				accts.add(buildAccount(rs));
			}
			
			return accts;
		} catch (SQLException e) {
			// TODO verify intended result
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account addAccount(Account a) {
		String sql = "INSERT INTO accounts VALUES (default, ?, ?) "
				+ "RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, a.getClient());
			ps.setDouble(2, a.getBalance());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			// TODO verify intended result
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account updateAccount(Account update) {
		String sql = "UPDATE accounts SET balance = ? WHERE id = ? "
				+ "RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setDouble(1, update.getBalance());
			ps.setInt(2, update.getId());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			//TODO verify intended result
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Account deleteAccount(int id) {
		String sql = "DELETE FROM accounts WHERE id = ? RETURNING *";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return buildAccount(rs);
			}
		} catch (SQLException e) {
			//TODO verify intended result
			e.printStackTrace();
		}

		return null;
	}

	private Account buildAccount(ResultSet rs) throws SQLException {
		Account acct = new Account();
		acct.setId(rs.getInt("id"));
		acct.setBalance(rs.getDouble("balance"));
		acct.setClient(rs.getInt("client"));
		
		return acct;
	}
}
