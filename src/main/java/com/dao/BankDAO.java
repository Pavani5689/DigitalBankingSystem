package com.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Customer;
import com.model.Transaction;

public class BankDAO {

	private static final String CUSTOMER_COLS = "id, name, email, password, age, gender, mobile, address, account_number, balance";
	private static final String TXN_COLS = "id, account_number, type, amount, balance_after, description, txn_date";

	// ---------- helpers ----------

	private Customer mapCustomer(ResultSet set) throws SQLException {
		Customer c = new Customer();
		c.setId(set.getInt("id"));
		c.setName(set.getString("name"));
		c.setEmail(set.getString("email"));
		c.setPassword(set.getString("password"));
		c.setAge(set.getInt("age"));
		c.setGender(set.getString("gender"));
		c.setMobile(set.getString("mobile"));
		c.setAddress(set.getString("address"));
		c.setAccountNumber(set.getLong("account_number"));
		c.setBalance(set.getBigDecimal("balance"));
		return c;
	}

	private Transaction mapTransaction(ResultSet set) throws SQLException {
		Transaction t = new Transaction();
		t.setId(set.getInt("id"));
		t.setAccountNumber(set.getLong("account_number"));
		t.setType(set.getString("type"));
		t.setAmount(set.getBigDecimal("amount"));
		t.setBalanceAfter(set.getBigDecimal("balance_after"));
		t.setDescription(set.getString("description"));
		t.setTxnDate(set.getTimestamp("txn_date"));
		return t;
	}

	private void insertTxn(Connection con, long acc, String type, BigDecimal amount, BigDecimal balanceAfter,
			String description) throws SQLException {
		String q = "insert into transactions(account_number, type, amount, balance_after, description) values(?,?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(q)) {
			ps.setLong(1, acc);
			ps.setString(2, type);
			ps.setBigDecimal(3, amount);
			ps.setBigDecimal(4, balanceAfter);
			ps.setString(5, description);
			ps.executeUpdate();
		}
	}

	// lock the account row and return its balance (null if the account does not exist)
	private BigDecimal lockBalance(Connection con, long acc) throws SQLException {
		try (PreparedStatement ps = con
				.prepareStatement("select balance from customer where account_number = ? for update")) {
			ps.setLong(1, acc);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return rs.getBigDecimal(1);
				}
			}
		}
		return null;
	}

	private void setBalance(Connection con, long acc, BigDecimal balance) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement("update customer set balance = ? where account_number = ?")) {
			ps.setBigDecimal(1, balance);
			ps.setLong(2, acc);
			ps.executeUpdate();
		}
	}

	private void rollbackQuietly(Connection con) {
		try {
			if (con != null) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeQuietly(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ---------- customers ----------

	public boolean emailExists(String email) {
		Connection con = ConnectionManager.getConnection();
		try (PreparedStatement ps = con.prepareStatement("select count(*) from customer where email = ?")) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				return rs.next() && rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return false;
	}

	/** Creates the customer, generates the account number and records the opening deposit. */
	public boolean saveCustomer(Customer c, BigDecimal openingDeposit) {
		Connection con = ConnectionManager.getConnection();
		try {
			String q = "insert into customer(name, email, password, age, gender, mobile, address, balance) values(?,?,?,?,?,?,?,?)";
			int id;
			try (PreparedStatement ps = con.prepareStatement(q, Statement.RETURN_GENERATED_KEYS)) {
				ps.setString(1, c.getName());
				ps.setString(2, c.getEmail());
				ps.setString(3, c.getPassword());
				ps.setInt(4, c.getAge());
				ps.setString(5, c.getGender());
				ps.setString(6, c.getMobile());
				ps.setString(7, c.getAddress());
				ps.setBigDecimal(8, openingDeposit);
				ps.executeUpdate();
				try (ResultSet keys = ps.getGeneratedKeys()) {
					keys.next();
					id = keys.getInt(1);
				}
			}
			long accNo = 1000000000L + id;
			try (PreparedStatement ps = con.prepareStatement("update customer set account_number = ? where id = ?")) {
				ps.setLong(1, accNo);
				ps.setInt(2, id);
				ps.executeUpdate();
			}
			if (openingDeposit.signum() > 0) {
				insertTxn(con, accNo, "DEPOSIT", openingDeposit, openingDeposit, "Opening deposit");
			}
			con.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
		} finally {
			closeQuietly(con);
		}
		return false;
	}

	public Customer checkLogin(String email, String password) {
		Connection con = ConnectionManager.getConnection();
		try (PreparedStatement ps = con
				.prepareStatement("select " + CUSTOMER_COLS + " from customer where email = ? and password = ?")) {
			ps.setString(1, email);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return mapCustomer(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return null;
	}

	public List<Customer> fetchAllCustomers() {
		List<Customer> list = new ArrayList<Customer>();
		Connection con = ConnectionManager.getConnection();
		try (PreparedStatement ps = con.prepareStatement("select " + CUSTOMER_COLS + " from customer order by id");
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapCustomer(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return list;
	}

	/** Returns a customer with id = 0 when nothing is found. */
	public Customer fetchByEmail(String email) {
		return fetchOne("email = ?", email);
	}

	public Customer fetchById(int id) {
		return fetchOne("id = ?", Integer.valueOf(id));
	}

	public Customer fetchByAccount(long accountNumber) {
		return fetchOne("account_number = ?", Long.valueOf(accountNumber));
	}

	private Customer fetchOne(String where, Object value) {
		Customer c = new Customer();
		Connection con = ConnectionManager.getConnection();
		try (PreparedStatement ps = con
				.prepareStatement("select " + CUSTOMER_COLS + " from customer where " + where)) {
			ps.setObject(1, value);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					c = mapCustomer(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return c;
	}

	public boolean editCustomer(Customer c) {
		Connection con = ConnectionManager.getConnection();
		String q = "update customer set name = ?, email = ?, age = ?, gender = ?, mobile = ?, address = ? where id = ?";
		try (PreparedStatement ps = con.prepareStatement(q)) {
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setInt(3, c.getAge());
			ps.setString(4, c.getGender());
			ps.setString(5, c.getMobile());
			ps.setString(6, c.getAddress());
			ps.setInt(7, c.getId());
			int count = ps.executeUpdate();
			con.commit();
			return count != 0;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
		} finally {
			closeQuietly(con);
		}
		return false;
	}

	public boolean deleteCustomer(int id) {
		Connection con = ConnectionManager.getConnection();
		try (PreparedStatement ps = con.prepareStatement("delete from customer where id = ?")) {
			ps.setInt(1, id);
			int count = ps.executeUpdate(); // transactions are removed by ON DELETE CASCADE
			con.commit();
			return count != 0;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
		} finally {
			closeQuietly(con);
		}
		return false;
	}

	// ---------- money ----------

	/** Returns null on success, otherwise an error message. */
	public String deposit(long acc, BigDecimal amount) {
		Connection con = ConnectionManager.getConnection();
		try {
			BigDecimal balance = lockBalance(con, acc);
			if (balance == null) {
				return "Account not found.";
			}
			BigDecimal newBalance = balance.add(amount);
			setBalance(con, acc, newBalance);
			insertTxn(con, acc, "DEPOSIT", amount, newBalance, "Cash deposit");
			con.commit();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
			return "Deposit failed. Please try again.";
		} finally {
			closeQuietly(con);
		}
	}

	/** Returns null on success, otherwise an error message. */
	public String withdraw(long acc, BigDecimal amount) {
		Connection con = ConnectionManager.getConnection();
		try {
			BigDecimal balance = lockBalance(con, acc);
			if (balance == null) {
				return "Account not found.";
			}
			if (balance.compareTo(amount) < 0) {
				rollbackQuietly(con);
				return "Insufficient balance.";
			}
			BigDecimal newBalance = balance.subtract(amount);
			setBalance(con, acc, newBalance);
			insertTxn(con, acc, "WITHDRAW", amount, newBalance, "Cash withdrawal");
			con.commit();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
			return "Withdrawal failed. Please try again.";
		} finally {
			closeQuietly(con);
		}
	}

	/** Moves money between two accounts in ONE database transaction. Returns null on success. */
	public String transfer(long from, long to, BigDecimal amount) {
		if (from == to) {
			return "You cannot transfer to your own account.";
		}
		Connection con = ConnectionManager.getConnection();
		try {
			// lock in a fixed order so two opposite transfers can never deadlock
			BigDecimal fromBal;
			BigDecimal toBal;
			if (from < to) {
				fromBal = lockBalance(con, from);
				toBal = lockBalance(con, to);
			} else {
				toBal = lockBalance(con, to);
				fromBal = lockBalance(con, from);
			}
			if (fromBal == null) {
				rollbackQuietly(con);
				return "Your account was not found.";
			}
			if (toBal == null) {
				rollbackQuietly(con);
				return "Receiver account number does not exist.";
			}
			if (fromBal.compareTo(amount) < 0) {
				rollbackQuietly(con);
				return "Insufficient balance.";
			}
			BigDecimal newFrom = fromBal.subtract(amount);
			BigDecimal newTo = toBal.add(amount);
			setBalance(con, from, newFrom);
			setBalance(con, to, newTo);
			insertTxn(con, from, "TRANSFER_OUT", amount, newFrom, "Transfer to A/C " + to);
			insertTxn(con, to, "TRANSFER_IN", amount, newTo, "Transfer from A/C " + from);
			con.commit();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			rollbackQuietly(con);
			return "Transfer failed. Nothing was deducted.";
		} finally {
			closeQuietly(con);
		}
	}

	// ---------- history ----------

	public List<Transaction> fetchHistory(long acc, int limit) {
		List<Transaction> list = new ArrayList<Transaction>();
		Connection con = ConnectionManager.getConnection();
		String q = "select " + TXN_COLS + " from transactions where account_number = ? order by txn_date desc, id desc"
				+ (limit > 0 ? " limit " + limit : "");
		try (PreparedStatement ps = con.prepareStatement(q)) {
			ps.setLong(1, acc);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					list.add(mapTransaction(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return list;
	}

	public List<Transaction> fetchAllTransactions() {
		List<Transaction> list = new ArrayList<Transaction>();
		Connection con = ConnectionManager.getConnection();
		String q = "select " + TXN_COLS + " from transactions order by txn_date desc, id desc";
		try (PreparedStatement ps = con.prepareStatement(q); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				list.add(mapTransaction(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(con);
		}
		return list;
	}
}
