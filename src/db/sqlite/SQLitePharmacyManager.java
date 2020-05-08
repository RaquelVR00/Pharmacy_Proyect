package db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.interfaces.PharmacyManager;

import db.pojos.Pharmacy;
public class SQLitePharmacyManager implements PharmacyManager {
	private Connection c;
	public SQLitePharmacyManager(Connection c) {
		this.c=c;
	}


	@Override
	public List<Pharmacy> searchByName(String name) {
		List <Pharmacy> pharmacyList = new ArrayList <Pharmacy> ();
		try {
			String sql = "SELECT * FROM pharmacy WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, "%"+name+"%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
			int id = rs.getInt("id");
			String pharmacyName = rs.getString("name");
			int pharmacyContract_pid = rs.getInt("contract_pid");
			String pharmacyLocation = rs.getString("location");
			Pharmacy newpharmacy = new Pharmacy(id,pharmacyName,pharmacyContract_pid,pharmacyLocation);
		   pharmacyList.add(newpharmacy);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pharmacyList;
	}


	@Override
	public void add(Pharmacy pharmacy) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO pharmacy (name, location, contract_pid) "
					+ "VALUES (?,?,?)";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pharmacy.getName());
			prep.setString(2, pharmacy.getLocation());
			prep.setInt(3, pharmacy.getContract_pid() );
			prep.executeUpdate();
			prep.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


