package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static final String USER = "root";
	static final String PASS = "nguyengiabao";
	
	@Override
	public List<BuildingEntity> findAll(String name) {
		String sql = "SELECT * FROM building WHERE name like '%" +name + "%'";
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql); ) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberOfBasement"));
				building.setWard(rs.getString("ward"));
				building.setStreet(rs.getString("street"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BuildingEntity> findAll1(String name, Integer districtId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM building WHERE 1 = 1 ");
		if (name != null && !name.equals("")) {
			sql.append("AND building.name LIKE '%" +name +"%' ");
		}
		if (districtId != null) {
			sql.append("AND building.districtId = " +districtId +" ");
		}
		List<BuildingEntity> result = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString()); ) {
			while(rs.next()) {
				BuildingEntity building = new BuildingEntity();
				building.setName(rs.getString("name"));
				building.setNumberOfBasement(rs.getInt("numberOfBasement"));
				building.setWard(rs.getString("ward"));
				building.setStreet(rs.getString("street"));
				result.add(building);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
